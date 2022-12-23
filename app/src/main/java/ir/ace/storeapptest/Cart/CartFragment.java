package ir.ace.storeapptest.Cart;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Detail.DetailActivity;
import ir.ace.storeapptest.Dialogs.DeleteBasketDailog;
import ir.ace.storeapptest.Models.BasketModel;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
    RecyclerView rvLists;
    Button btnKeepOn;
    ImageView imvEmpty;
    TextView txtTotalPrice,txtTryAgin,txtEmpty;
    CartViewModel cartViewModel = new CartViewModel();
    CompositeDisposable cd = new CompositeDisposable();
    RvBasketAdapter rvBasketAdapter;
    List<Integer> sumList;
    RelativeLayout rlLoading;
    ProgressBar pb;
    LinearLayout linLay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_cart, container, false);

        }
        setUpViews();
        getBasketItems();
        return view;
    }


    private void setUpViews() {
        sumList =new ArrayList<>();
        txtEmpty = view.findViewById(R.id.txt_CartFragment_emptycart);
        imvEmpty = view.findViewById(R.id.imv_CartFragment_empty);
        linLay = view.findViewById(R.id.linlay_CartFragment);
        pb = view.findViewById(R.id.pb_CartFragment_loading);
        rlLoading = view.findViewById(R.id.rvlayout_CartFragment_parent);
        rvLists = view.findViewById(R.id.rv_CartFragment_items);
        btnKeepOn = view.findViewById(R.id.btn_CartFragment_keepOnBuy);
        txtTotalPrice = view.findViewById(R.id.txt_CartFragment_totalPrice);
        txtTryAgin = view.findViewById(R.id.txt_CartFragment_tryagain);

        txtTryAgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLay.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);
                getBasketItems();
            }
        });

        btnKeepOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.43.127/app/p"));
                getActivity().startActivity(i);
            }
        });
        rvLists.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }

    private void getBasketItems() {
        cartViewModel.getBasketItems(cartViewModel.getPhone(getActivity())).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<BasketModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<BasketModel> basketModels) {

                        if (basketModels.size()!=0){
                            txtEmpty.setVisibility(View.GONE);
                            imvEmpty.setVisibility(View.GONE);
                        for (int i = 0; i < basketModels.size(); i++) {
                            sumList.add(Integer.parseInt(basketModels.get(i).getPrice().replace(",","").replace("تومان","").trim()));
                        }
                        int sum = 0;
                        for (int i2 = 0; i2 < sumList.size(); i2++) {
                            sum += sumList.get(i2);

                        }

                        if (sum != 0) {
                            txtTotalPrice.setText(sum + "  تومان");
                        }
                        rvBasketAdapter=new RvBasketAdapter(getActivity(), basketModels, new RvBasketAdapter.OnBasketParentClick() {
                            @Override
                            public void OnClick(String id) {
                                Intent i = new Intent(getActivity(), DetailActivity.class);
                                i.putExtra("id", id);
                                startActivity(i);
                            }
                        }, new RvBasketAdapter.OnDeleteBasketClick() {
                            @Override
                            public void OnClick(BasketModel basketModel) {
                                DeleteBasketDailog db = new DeleteBasketDailog();
                                db.setOnDeleteDialogFavorClick(new DeleteBasketDailog.OnDeleteDialogFavorClick() {
                                    @Override
                                    public void onClick(String detre) {
                                        if (detre.equals("Y")) {
                                            cartViewModel.deleteFromBasket(String.valueOf(basketModel.getBasketId())).subscribeOn(Schedulers.newThread())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribe(new SingleObserver<MessageModel>() {
                                                        @Override
                                                        public void onSubscribe(@NonNull Disposable d) {
                                                            cd.add(d);
                                                        }

                                                        @Override
                                                        public void onSuccess(@NonNull MessageModel messageModel) {
                                                            if (messageModel.getStatus().equals("success")) {

                                                                basketModels.remove(basketModel);
                                                                rvBasketAdapter.notifyItemRemoved(Integer.parseInt(String.valueOf(basketModel.getBasketId())) - 1);
                                                                rvBasketAdapter.notifyDataSetChanged();
                                                                db.dismiss();
                                                                Toast.makeText(getActivity(), "محصول با موفقیت از سبد خرید حذف شد!", Toast.LENGTH_SHORT).show();

                                                            } else {
                                                                Toast.makeText(getActivity(), "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }

                                                        @Override
                                                        public void onError(@NonNull Throwable e) {
                                                            Toast.makeText(getActivity(), "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();

                                                            Log.e("ACE", "onError: " + e.toString());
                                                        }
                                                    });


                                        } else {
                                            db.dismiss();
                                        }
                                    }
                                });
                                db.show(getChildFragmentManager(), "");
                            }
                        });
                        rvLists.setAdapter(rvBasketAdapter);
                        }
                        rlLoading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(getActivity(), "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                        linLay.setVisibility(View.VISIBLE);
                        Log.e("ACE", "onError: " + e );
                    }
                });

    }

    @Override
    public void onDetach() {
        cd.dispose();
        super.onDetach();
    }
}