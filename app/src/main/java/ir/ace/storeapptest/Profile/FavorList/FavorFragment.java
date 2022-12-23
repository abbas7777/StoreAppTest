package ir.ace.storeapptest.Profile.FavorList;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Detail.DetailActivity;
import ir.ace.storeapptest.Dialogs.DeleteFavoDailog;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.R;
import ir.ace.storeapptest.Search.SearchActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavorFragment newInstance(String param1, String param2) {
        FavorFragment fragment = new FavorFragment();
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
    ImageView imvClose;
    RecyclerView rvFavor;
    FavorViewModel favorViewModel = new FavorViewModel();
    String phone;
    CompositeDisposable cd = new CompositeDisposable();
    RvFavorAdapter rvFavorAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_favor, container, false);
        }
        setUpViews();
        phone = getArguments().getString("phone");
        getFavorites();
        return view;
    }

    private void setUpViews() {
        imvClose = view.findViewById(R.id.imv_FavorFragment_close);
        rvFavor = view.findViewById(R.id.rv_FavorFragment_list);

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rvFavor.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }


    private void getFavorites() {
        favorViewModel.getFavotites(phone).observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<Product> productList) {

                        rvFavorAdapter=new RvFavorAdapter(productList, getActivity(), new RvFavorAdapter.OnDeleteClick() {
                            @Override
                            public void onClick(String id,int p) {
                                DeleteFavoDailog deleteFavoDailog=new DeleteFavoDailog();
                                deleteFavoDailog.setOnDeleteDialogFavorClick(new DeleteFavoDailog.OnDeleteDialogFavorClick() {
                                    @Override
                                    public void onClick(String detre) {
                                        if (detre.equals("Y")){
                                            favorViewModel.deletFavorit(id, phone).observeOn(Schedulers.newThread())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribe(new SingleObserver<MessageModel>() {
                                                        @Override
                                                        public void onSubscribe(@NonNull Disposable d) {
                                                            cd.add(d);
                                                        }

                                                        @Override
                                                        public void onSuccess(@NonNull MessageModel messageModel) {
                                                            if (messageModel.getStatus().equals("success")) {

                                                                productList.remove(p);
                                                                rvFavorAdapter.notifyItemRemoved(p);
                                                                rvFavorAdapter.notifyDataSetChanged();
                                                                deleteFavoDailog.dismiss();
                                                                Toast.makeText(getActivity(), "محصول با موفقیت از علافه مندی ها حذف شد!", Toast.LENGTH_SHORT).show();

                                                            }else {
                                                                Toast.makeText(getActivity(), "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }

                                                        @Override
                                                        public void onError(@NonNull Throwable e) {
                                                            Toast.makeText(getActivity(), "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();

                                                            Log.e("ACE", "onError: " + e.toString());
                                                        }
                                                    });


                                        }else{
                                            deleteFavoDailog.dismiss();
                                        }
                                    }
                                });
                                deleteFavoDailog.show(getChildFragmentManager(),"");
                            }
                        }, new RvFavorAdapter.OnParentClick() {
                            @Override
                            public void onClick(String id) {
                                Intent intent = new Intent(getActivity(), DetailActivity.class);
                                intent.putExtra("id", id);
                                startActivity(intent);
                            }
                        });
                        rvFavor.setAdapter(rvFavorAdapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Log.e("ACE", "onError: " + e);
                    }
                });
    }

}