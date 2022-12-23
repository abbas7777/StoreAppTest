package ir.ace.storeapptest.Properties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Models.PropertiesItem;
import ir.ace.storeapptest.R;

public class PropertiesActivity extends AppCompatActivity {

    ImageView imvClose;
    RecyclerView rvProperties;
    PropertiesViewModel viewModel = new PropertiesViewModel();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);

        setUpViews();
        getSingleProperties();
    }


    private void setUpViews() {
        imvClose = findViewById(R.id.imv_PropertiesActivity_close);
        rvProperties = findViewById(R.id.rv_PropertiesActivity_properties);

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvProperties.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
    }

    private void getSingleProperties() {
        viewModel.getProperties().observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PropertiesItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<PropertiesItem> propertiesItems) {

                        rvProperties.setAdapter(new RvPropertiesAdapter(getApplicationContext(),propertiesItems));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("ACE", "onError: " + e.toString());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}