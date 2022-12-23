package ir.ace.storeapptest.Profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.ace.storeapptest.ConnectLayout.ConnectActivity;
import ir.ace.storeapptest.Dialogs.LogOutDailog;
import ir.ace.storeapptest.Main.MainActivity;
import ir.ace.storeapptest.Models.ButtonProfileModel;
import ir.ace.storeapptest.Profile.AccountInfos.AccountInfosFragment;
import ir.ace.storeapptest.Profile.FavorList.FavorFragment;
import ir.ace.storeapptest.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;
    ImageView imvprofile;
    TextView txtName, txtPhone, txtLogin;
    RecyclerView rvItems;
    RelativeLayout rlLoading;
    CardView cvWarring;
    ProgressBar pb;
    ProfileViewModel profileViewModel = new ProfileViewModel();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_profile, container, false);
        }
        setUpViews();

        if (profileViewModel.getPhone(getActivity()).equals("")) {

            pb.setVisibility(View.GONE);
            cvWarring.setVisibility(View.VISIBLE);

        } else {
            setDataForButton();
            txtName.setText(profileViewModel.getName(getActivity()));
            txtPhone.setText(profileViewModel.getPhone(getActivity()));
            cvWarring.setVisibility(View.GONE);
            rlLoading.setVisibility(View.GONE);
        }
        return view;
    }


    private void setUpViews() {
        imvprofile = view.findViewById(R.id.imv_ProfileFragment_imageprofile);
        txtName = view.findViewById(R.id.txt_ProfileFragment_nameuser);
        txtPhone = view.findViewById(R.id.txt_ProfileFragment_phone);
        rvItems = view.findViewById(R.id.rv_ProfileFragment_items);
        rlLoading = view.findViewById(R.id.rlv_ProfileFragment_loading);
        cvWarring = view.findViewById(R.id.cv_ProfileFragment_warring);
        pb = view.findViewById(R.id.pb_ProfileFragment_loading);
        txtLogin = view.findViewById(R.id.txt_ProfileFragment_login);

        rvItems.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ConnectActivity.class));
            }
        });
    }


    private void setDataForButton() {
        List<ButtonProfileModel> bl = new ArrayList<>();
        ButtonProfileModel bmInfosAcc = new ButtonProfileModel();
        ButtonProfileModel bmFavor = new ButtonProfileModel();
        ButtonProfileModel bmOrder = new ButtonProfileModel();
        ButtonProfileModel bmSetPoint = new ButtonProfileModel();
        ButtonProfileModel bmExite = new ButtonProfileModel();

        bmInfosAcc.setTitle(getResources().getString(R.string.btn_name_prrivateinfo));
        bmFavor.setTitle(getResources().getString(R.string.btn_name_favor));
        bmOrder.setTitle(getResources().getString(R.string.btn_name_order));
        bmSetPoint.setTitle(getResources().getString(R.string.btn_name_setPoint));
        bmExite.setTitle(getResources().getString(R.string.btn_name_exit));

        bmInfosAcc.setIdIcon(R.drawable.editprofile_vector);
        bmFavor.setIdIcon(R.drawable.ic_baseline_favorite_border_24);
        bmOrder.setIdIcon(R.drawable.order_vector);
        bmSetPoint.setIdIcon(R.drawable.set_start_vector);
        bmExite.setIdIcon(R.drawable.logout_vector);

        bl.add(bmInfosAcc);
        bl.add(bmFavor);
        bl.add(bmOrder);
        bl.add(bmSetPoint);
        bl.add(bmExite);

        String f = getResources().getString(R.string.btn_name_prrivateinfo).toString();
        rvItems.setAdapter(new RvBtnProfileAdapter(getActivity(), bl, new RvBtnProfileAdapter.OnBtnProfileClick() {
            @Override
            public void onClick(int i) {
                switch (i) {

                    case R.drawable.editprofile_vector:

                        loadFragment(new AccountInfosFragment());
                        break;

                    case R.drawable.ic_baseline_favorite_border_24:

                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        Fragment fragment = new FavorFragment();
                        Bundle arg = new Bundle();
                        arg.putString("phone", profileViewModel.getPhone(getActivity()));
                        fragment.setArguments(arg);
                        transaction.replace(R.id.frame_ProfileFragment_host, fragment);
                        transaction.addToBackStack("f");
                        transaction.commit();
                        break;


                    case R.drawable.order_vector:

                        break;


                    case R.drawable.set_start_vector:

                        break;


                    case R.drawable.logout_vector:

                        LogOutDailog logOutDailog = new LogOutDailog();
                        logOutDailog.setOnDeleteDialogFavorClick(new LogOutDailog.OnDeleteDialogFavorClick() {
                            @Override
                            public void onClick(String detre) {
                                if (detre.equals("Y")) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            profileViewModel.deleteSherP(getActivity());
                                            Intent i = new Intent(getActivity(), MainActivity.class);
                                            getActivity().startActivity(i);
                                        }
                                    }).start();

                                    logOutDailog.dismiss();

                                } else {
                                    logOutDailog.dismiss();
                                }
                            }
                        });
                        logOutDailog.show(getChildFragmentManager(), "");
                        break;

                }
            }
        }));
    }


    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_ProfileFragment_host, fragment);
        transaction.addToBackStack("f");
        transaction.commit();
    }

}