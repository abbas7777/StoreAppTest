package ir.ace.storeapptest.Daste.EnLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Detail.DetailActivity;
import ir.ace.storeapptest.Models.FilterModel;
import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.Models.ValuesItem;
import ir.ace.storeapptest.R;
import ir.ace.storeapptest.Search.RvSearchAdapter;

public class EnLayoutActivity extends AppCompatActivity implements FilterFragment.OnValuesList {

    TextView txtTitle, txtSort, txtFilter, txtTryAgain;
    RadioButton rbtnMostSell, rbtnMostView, rbtnHighestPrice, rbtnLPrice, rbtnNewPro;
    RecyclerView rvProducts, rvFilterItem;
    String title;
    int sitution;
    ImageView imvClose;
    RelativeLayout rlLoading, rlToolbar;
    LinearLayout linearErorr, linearSort;
    List<FilterModel> filterModelList;
    ProgressBar pb;
    Button btnFilter;
    List<ValuesItem> valuesItemList, valuesItemSendList;
    ExpandableLayout expSort, expFilter;
    EnLayoutViewModel enLayoutViewModel = new EnLayoutViewModel();
    CompositeDisposable cd = new CompositeDisposable();
    FilterFragment.OnValuesList onValuesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_layout);
        title = getIntent().getExtras().getString("title");
        setUpViews();
        getProduct(2);
        getFilterItem();
    }


    private void setUpViews() {
        filterModelList = new ArrayList<>();
        valuesItemSendList = new ArrayList<>();
        txtTitle = findViewById(R.id.txt_EnLayoutActivity_title);
        txtSort = findViewById(R.id.txt_EnLayoutActivity_sort);
        btnFilter = findViewById(R.id.btn_EnLayoutActivity_filter);
        rlToolbar = findViewById(R.id.rlLayout_EnLayoutActivity_toolbar);
        txtFilter = findViewById(R.id.txt_EnLayoutActivity_filter);
        rvProducts = findViewById(R.id.rv_EnLayoutActivity_products);
        rvFilterItem = findViewById(R.id.rv_EnLayoutActivity_filterItem);
        imvClose = findViewById(R.id.imv_EnLayoutActivity_close);
        expSort = findViewById(R.id.exp_EnlayoutActivity_sort);
        expFilter = findViewById(R.id.exp_EnlayoutActivity_filter);
        rbtnMostSell = findViewById(R.id.rbtn_EnLayoutActivity_mostSell);
        rbtnMostView = findViewById(R.id.rbtn_EnLayoutActivity_mostView);
        rbtnHighestPrice = findViewById(R.id.rbtn_EnLayoutActivity_highestPrice);
        rbtnLPrice = findViewById(R.id.rbtn_EnLayoutActivity_lowestPrice);
        rbtnNewPro = findViewById(R.id.rbtn_EnLayoutActivity_newProduct);
        rlLoading = findViewById(R.id.rlLayout_EnLayoutActivity_parent);
        linearErorr = findViewById(R.id.linlay_EnLayoutActivity);
        linearSort = findViewById(R.id.linlayout_EnLayoutActivity_sort);
        pb = findViewById(R.id.pb_EnLayoutActivity_loading);
        txtTryAgain = findViewById(R.id.txt_EnLayoutActivity_tryagain);
        rbtnMostSell.setSelected(true);
        sitution = 2;
        txtTitle.setText(title);
        rvProducts.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvFilterItem.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlToolbar.setVisibility(View.VISIBLE);
                linearSort.setVisibility(View.VISIBLE);
                expFilter.setExpanded(false);
                enLayoutViewModel.getFilterProduct(valuesItemSendList).observeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Product>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                cd.add(d);
                            }

                            @Override
                            public void onSuccess(@NonNull List<Product> productList) {

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                                Log.e("ACE", "onError: "+e );
                            }
                        });

            }
        });

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearErorr.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);
                getProduct(sitution);
            }
        });

        txtSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expSort.isExpanded()) {
                    expSort.setExpanded(false);

                } else {

                    expSort.setExpanded(true);
                }
            }
        });
        txtFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expFilter.isExpanded()) {
                    expFilter.setExpanded(false);

                } else {

                    expFilter.setExpanded(true);
                }
            }
        });

        rbtnMostSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbtnMostSell.isSelected()) {

//                   rbtnMostSell.setChecked(false);
//                   rbtnMostSell.setSelected(false);

                } else {
                    rbtnMostSell.setChecked(true);
                    rbtnMostSell.setSelected(true);
                    rbtnMostView.setSelected(false);
                    rbtnMostView.setChecked(false);
                    rbtnHighestPrice.setSelected(false);
                    rbtnHighestPrice.setChecked(false);
                    rbtnLPrice.setSelected(false);
                    rbtnLPrice.setChecked(false);
                    rbtnNewPro.setSelected(false);
                    rbtnNewPro.setChecked(false);
                    getProduct(2);
                    sitution = 2;
                    expSort.setExpanded(false);

                }
            }
        });

        rbtnMostView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbtnMostView.isSelected()) {
//
//                    rbtnMostView.setChecked(false);
//                    rbtnMostView.setSelected(false);

                } else {
                    rbtnMostSell.setChecked(false);
                    rbtnMostSell.setSelected(false);
                    rbtnMostView.setSelected(true);
                    rbtnMostView.setChecked(true);
                    rbtnHighestPrice.setSelected(false);
                    rbtnHighestPrice.setChecked(false);
                    rbtnLPrice.setSelected(false);
                    rbtnLPrice.setChecked(false);
                    rbtnNewPro.setSelected(false);
                    rbtnNewPro.setChecked(false);
                    getProduct(1);
                    sitution = 1;
                    expSort.setExpanded(false);


                }
            }
        });


        rbtnHighestPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbtnHighestPrice.isSelected()) {

//                    rbtnHighestPrice.setChecked(false);
//                    rbtnHighestPrice.setSelected(false);

                } else {
                    rbtnMostSell.setChecked(false);
                    rbtnMostSell.setSelected(false);
                    rbtnMostView.setSelected(false);
                    rbtnMostView.setChecked(false);
                    rbtnHighestPrice.setSelected(true);
                    rbtnHighestPrice.setChecked(true);
                    rbtnLPrice.setSelected(false);
                    rbtnLPrice.setChecked(false);
                    rbtnNewPro.setSelected(false);
                    rbtnNewPro.setChecked(false);
                    getProduct(3);
                    sitution = 3;
                    expSort.setExpanded(false);

                }
            }
        });


        rbtnLPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbtnLPrice.isSelected()) {

//                    rbtnLPrice.setChecked(false);
//                    rbtnLPrice.setSelected(false);

                } else {
                    rbtnMostSell.setChecked(false);
                    rbtnMostSell.setSelected(false);
                    rbtnMostView.setSelected(false);
                    rbtnMostView.setChecked(false);
                    rbtnHighestPrice.setSelected(false);
                    rbtnHighestPrice.setChecked(false);
                    rbtnLPrice.setSelected(true);
                    rbtnLPrice.setChecked(true);
                    rbtnNewPro.setSelected(false);
                    rbtnNewPro.setChecked(false);
                    getProduct(4);
                    sitution = 4;
                    expSort.setExpanded(false);


                }
            }
        });


        rbtnNewPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbtnNewPro.isSelected()) {

//                    rbtnNewPro.setChecked(false);
//                    rbtnNewPro.setSelected(false);

                } else {
                    rbtnMostSell.setChecked(false);
                    rbtnMostSell.setSelected(false);
                    rbtnMostView.setSelected(false);
                    rbtnMostView.setChecked(false);
                    rbtnHighestPrice.setSelected(false);
                    rbtnHighestPrice.setChecked(false);
                    rbtnLPrice.setSelected(false);
                    rbtnLPrice.setChecked(false);
                    rbtnNewPro.setSelected(true);
                    rbtnNewPro.setChecked(true);
                    getProduct(5);
                    sitution = 5;
                    expSort.setExpanded(false);


                }
            }
        });


    }

    private void getProduct(int sort) {
        RvSearchAdapter rvSearchAdapter = new RvSearchAdapter(EnLayoutActivity.this, new RvSearchAdapter.OnClickParent() {
            @Override
            public void onClick(String id) {
                Intent intent = new Intent(EnLayoutActivity.this, DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        rvProducts.setAdapter(rvSearchAdapter);

        enLayoutViewModel.getCatProduct(title, sort)
                .observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<Product> productList) {
                        rlLoading.setVisibility(View.GONE);
                        rvSearchAdapter.onBind(productList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        pb.setVisibility(View.GONE);
                        rlLoading.setVisibility(View.VISIBLE);
                        linearErorr.setVisibility(View.VISIBLE);

                        Log.e("ACE", "onError: " + e);
                    }
                });
    }

    private void getFilterItem() {
        enLayoutViewModel.getFilterProduct(title)
                .observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<Product> productList) {
                        try {
                            JSONArray array = new JSONArray(productList.get(1).getFilterItem());
                            for (int i = 0; i < array.length(); i++) {

                                FilterModel filterModel = new FilterModel();
                                JSONObject object = array.getJSONObject(i);
                                filterModel.setTitle(object.getString("title"));
                                JSONArray jsonArray = object.getJSONArray("values");
                                valuesItemList = new ArrayList<>();

                                for (int j = 0; j < jsonArray.length(); j++) {

                                    ValuesItem valuesItem = new ValuesItem();
                                    JSONObject valObject = jsonArray.getJSONObject(j);
                                    valuesItem.setTitle(valObject.getString("title"));
                                    valuesItemList.add(valuesItem);
                                }

                                filterModel.setValues(valuesItemList);
                                filterModelList.add(filterModel);
                            }

                            rvFilterItem.setAdapter(new RvFilterItemAdapter(EnLayoutActivity.this, filterModelList, new RvFilterItemAdapter.OnClickItemFilter() {
                                @Override
                                public void onClick(List<ValuesItem> valuesItemList, int p) {

                                    FilterFragment fragment = new FilterFragment();

                                    Bundle bundle = new Bundle();
                                    bundle.putInt("p", p);
                                    bundle.putParcelableArrayList("List", (ArrayList<? extends Parcelable>) valuesItemList);
                                    fragment.setArguments(bundle);
                                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                    transaction.replace(R.id.exp_EnlayoutActivity_filter, fragment);
                                    transaction.addToBackStack("f");
                                    transaction.commit();
                                    linearSort.setVisibility(View.GONE);
                                    rlToolbar.setVisibility(View.GONE);
                                }
                            }));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Log.e("ACE", "onError: " + e);
                    }
                });
    }


    @Override
    protected void onDestroy() {
        cd.dispose();
        super.onDestroy();
    }

    @Override
    public void onClick(List<ValuesItem> valuesItemList, int p) {

        FilterModel filterModel = filterModelList.get(p);
        filterModel.setValues(valuesItemList);
        filterModelList.set(p, filterModel);
        rvFilterItem.setAdapter(new RvFilterItemAdapter(EnLayoutActivity.this, filterModelList, new RvFilterItemAdapter.OnClickItemFilter() {
            @Override
            public void onClick(List<ValuesItem> valuesItemList, int p) {

                FilterFragment fragment = new FilterFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("List", (ArrayList<? extends Parcelable>) valuesItemList);
                bundle.putInt("p", p);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.exp_EnlayoutActivity_filter, fragment);
                transaction.addToBackStack("f");
                transaction.commit();
                linearSort.setVisibility(View.GONE);
                rlToolbar.setVisibility(View.GONE);
            }
        }));

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < valuesItemList.size(); i++) {
                    if (valuesItemList.get(i).isCheck()) {
                        valuesItemSendList.add(valuesItemList.get(i));
                    }
                }
            }
        }).start();

    }
}