package ir.ace.storeapptest.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Detail.DetailActivity;
import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.R;

public class SearchActivity extends AppCompatActivity {

    ImageView imvClose;
    RecyclerView rvItems;
    EditText etxtSearch;
    SearchViewModel searchViewModel = new SearchViewModel();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RvSearchAdapter rvSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setUpViews();

    }

    private void setUpViews() {
        imvClose = findViewById(R.id.imv_SearchActivity_close);
        rvItems = findViewById(R.id.rv_SearchActivity_items);
        etxtSearch = findViewById(R.id.etxt_SearchActivity_search);
        rvItems.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvSearchAdapter = new RvSearchAdapter(this, new RvSearchAdapter.OnClickParent() {
            @Override
            public void onClick(String id) {
                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        rvItems.setAdapter(rvSearchAdapter);

        etxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!etxtSearch.getText().toString().equals("")) {
                    searchViewModel.getSearchProduct(String.valueOf(s)).observeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<List<Product>>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    compositeDisposable.add(d);

                                }

                                @Override
                                public void onSuccess(@NonNull List<Product> productList) {
                                    rvSearchAdapter.onBind(productList);

                                }

                                @Override
                                public void onError(@NonNull Throwable e) {

                                    Log.e("ACE", "onError: " + e);
                                }
                            });

                }else{
                    rvSearchAdapter.onBind(new ArrayList<>());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}