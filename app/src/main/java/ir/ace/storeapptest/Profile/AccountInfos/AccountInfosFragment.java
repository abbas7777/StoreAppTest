package ir.ace.storeapptest.Profile.AccountInfos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Dialogs.LoadingDialog;
import ir.ace.storeapptest.Models.ProfileModel;
import ir.ace.storeapptest.R;
import ir.ace.storeapptest.SharePerf.SharePeDataُService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountInfosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountInfosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountInfosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountInfosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountInfosFragment newInstance(String param1, String param2) {
        AccountInfosFragment fragment = new AccountInfosFragment();
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

    ImageView imvClose;
    TextInputEditText etxtName, etxtPhone, etxtAddress, etxtCode;
    String sName, sAddress, sCodePosti, phone, name;
    Button btnSumbit;
    View view;
    RelativeLayout rlLoading;
    LinearLayout linUnConnect;
    TextView txtTryAgain;
    ProgressBar pb;
    AccountInfosViewModel accountInfosViewModel = new AccountInfosViewModel();
    CompositeDisposable cd = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_account_infos, container, false);
        }
        setUpViews();
        getInfos();
        return view;
    }

    private void setUpViews() {
        phone = accountInfosViewModel.getPhone(getActivity());
        name = accountInfosViewModel.getName(getActivity());
        rlLoading = view.findViewById(R.id.rtl_AccountInfosFragment_parent);
        linUnConnect = view.findViewById(R.id.linlay_AccountInfosFragment);
        txtTryAgain = view.findViewById(R.id.txt_AccountInfosFragment_tryagain);
        pb = view.findViewById(R.id.pb_AccountInfosFragment_loading);
        imvClose = view.findViewById(R.id.imv_AccountInfosFragment_close);
        etxtName = view.findViewById(R.id.etxt_AccountInfosFragment_name);
        etxtPhone = view.findViewById(R.id.etxt_AccountInfosFragment_phone);
        etxtAddress = view.findViewById(R.id.etxt_AccountInfosFragment_address);
        etxtCode = view.findViewById(R.id.etxt_AccountInfosFragment_codePosti);
        btnSumbit = view.findViewById(R.id.btn_AccountInfosFragment_sumbit);

        etxtName.setText(name);
        etxtPhone.setText(phone);
        etxtPhone.setEnabled(false);
        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txtTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linUnConnect.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);
                getInfos();
            }
        });
        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etxtAddress.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "آدرس خود راوارد کنید!", Toast.LENGTH_SHORT).show();

                } else if (etxtCode.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "کد پستی خود را وارد کنید!", Toast.LENGTH_SHORT).show();

                } else {
                    if (!etxtName.getText().toString().equals(name) ||
                            !etxtAddress.getText().toString().equals(sAddress) ||
                            !etxtCode.getText().toString().equals(sCodePosti)) {

                        LoadingDialog dialog = new LoadingDialog();
                        dialog.show(getChildFragmentManager(), "");

                        accountInfosViewModel.updateInfos(etxtName.getText().toString(), phone, etxtAddress.getText().toString(), etxtCode.getText().toString())
                                .observeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<String>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {
                                        cd.add(d);
                                    }

                                    @Override
                                    public void onSuccess(@NonNull String s) {

                                        if (s.equals("s")) {

                                            if (!etxtName.getText().toString().equals(accountInfosViewModel.getName(getActivity()))) {

                                                SharePeDataُService sharePeDataُService = new SharePeDataُService(getActivity());
                                                sharePeDataُService.updateName(etxtName.getText().toString());

                                            }
                                            Toast.makeText(getActivity(), "اطلاعات با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();

                                            dialog.dismiss();
                                        } else {
                                            Toast.makeText(getActivity(), "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();

                                        }
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Log.e("ACE", "onError: " + e);
                                    }
                                });


                    } else {

                        Toast.makeText(getActivity(), "این اطلاعات قبلا ثبت شده است!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

    public void getInfos() {

        accountInfosViewModel.getProfileInfos(phone).observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ProfileModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<ProfileModel> profileModels) {
                        ProfileModel pm = profileModels.get(0);
                        sAddress = pm.getAddress();
                        sCodePosti = pm.getCodeposti();
                        etxtAddress.setText(pm.getAddress().equals("") ? "" : pm.getAddress());
                        etxtCode.setText(pm.getCodeposti().equals("") ? "" : pm.getCodeposti());
                        rlLoading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        pb.setVisibility(View.GONE);
                        linUnConnect.setVisibility(View.VISIBLE);
                        Log.e("ACE", "onError: " + e);
                    }
                });

    }
}