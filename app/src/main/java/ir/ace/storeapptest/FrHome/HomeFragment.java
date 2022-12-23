package ir.ace.storeapptest.FrHome;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Daste.EnLayout.EnLayoutActivity;
import ir.ace.storeapptest.Detail.DetailActivity;
import ir.ace.storeapptest.Models.Banner;
import ir.ace.storeapptest.Models.CatsItem;
import ir.ace.storeapptest.Models.NewProduct;
import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.R;
import ss.com.bannerslider.Slider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    RecyclerView rvWonderfuls, rvCats, rvimages, rvnewproduct;
    ss.com.bannerslider.Slider slider;
    HomeViewModel homeViewModel = new HomeViewModel();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }
        setUpViews();
        getCats();
        getProducts();
        getBanners();
        getNewProduct();
        return view;
    }


    private void setUpViews() {
        slider = view.findViewById(R.id.baner_HomeFragment_sliders);
        Slider.init(new PicassoImageLoadingService(getActivity()));
        rvWonderfuls = view.findViewById(R.id.rv_HomeFragment_productcloseshow);
        rvCats = view.findViewById(R.id.rv_HomeFragment_catgorys);
        rvimages = view.findViewById(R.id.rv_HomeFragment_images);
        rvnewproduct = view.findViewById(R.id.rv_HomeFragment_newproduct);

        rvCats.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvimages.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        rvnewproduct.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        rvWonderfuls.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }

    private void getCats() {
        homeViewModel.getCats().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<CatsItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<CatsItem> catsItems) {

                        rvCats.setAdapter(new RvCatsAdapter(getContext(), catsItems, new RvCatsAdapter.OnClickCatsItem() {
                            @Override
                            public void onClick(String title) {
                                Intent i = new Intent(getActivity(), EnLayoutActivity.class);
                                i.putExtra("title", title);
                                getActivity().startActivity(i);
                            }
                        }));
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("ACE", "onError: " + e.toString());
                    }
                });
    }

    private void getProducts() {
        homeViewModel.getProduct().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onSuccess(List<Product> products) {

                        rvWonderfuls.setAdapter(new RvProductsAdapter(getActivity(), products, new RvProductsAdapter.OnClick() {
                            @Override
                            public void onClik(String id) {
                                Intent intent = new Intent(getActivity(), DetailActivity.class);
                                intent.putExtra("id", id);
                                getActivity().startActivity(intent);
                            }
                        }));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ACE", "onErrorproducts: " + e.toString());

                    }
                });
    }

    private void getBanners() {
        homeViewModel.getBanner().observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Banner> banners) {

                        List<Banner> sliders = new ArrayList<>();
                        List<Banner> images = new ArrayList<>();
                        for (int i = 0; i < banners.size(); i++) {
                            if (banners.get(i).getType().equals("0")) {
                                sliders.add(banners.get(i));
                            } else {
                                images.add(banners.get(i));
                            }
                        }
                        slider.setAdapter(new BanerAdapter(sliders));
                        rvimages.setAdapter(new RvImagesAdapter(getActivity(), images));

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("ACE", "onErrorbanner: " + e.toString());
                    }
                });
    }

    private void getNewProduct() {
        homeViewModel.getNewProduct().observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<NewProduct>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<NewProduct> newProducts) {

                        rvnewproduct.setAdapter(new RvNewProductAdapter(getActivity(), newProducts, new RvNewProductAdapter.OnNewProductItemClick() {
                            @Override
                            public void onClick(String id) {
                                Intent intent = new Intent(getActivity(), DetailActivity.class);
                                intent.putExtra("id", id);
                                getActivity().startActivity(intent);
                            }
                        }));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        super.onDetach();
    }
}