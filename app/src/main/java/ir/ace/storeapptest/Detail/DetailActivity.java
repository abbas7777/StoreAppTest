package ir.ace.storeapptest.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
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
import ir.ace.storeapptest.Comments.CommentActivity;
import ir.ace.storeapptest.Dialogs.DeleteFavoDailog;
import ir.ace.storeapptest.Main.MainActivity;
import ir.ace.storeapptest.Models.Detail;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.Properties.PropertiesActivity;
import ir.ace.storeapptest.R;
import ss.com.bannerslider.Slider;

public class DetailActivity extends AppCompatActivity {

    DetailViewModel detailViewModel = new DetailViewModel();
    ImageView imvcart, imvclose, imvaddtofav;
    Slider slider;
    RecyclerView rvcomments;
    Button btnproperties, btnadd, btnComments;
    RatingBar ratingBar;
    TextView txtname, txtexplan, txtprice, txtpprice, txtTryAgain,txtcart;
    LinearLayout linearLayout;
    RelativeLayout relativeLayout, toolbar;
    ProgressBar progressBar, pbCart;
    String id, phone, sName;
    CardView cvToolbar;
    NestedScrollView nestedScrollView;
    int trancyToolbar = 0;
    int trancyScrollView = 0;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setUpViews();
        getDetails();
    }

    private void setUpViews() {
        id = getIntent().getExtras().getString("id");
        phone = detailViewModel.getPhone(DetailActivity.this);
        btnadd = findViewById(R.id.btn_DetailActivity_addtocart);
        btnproperties = findViewById(R.id.btn_DetailActivity_properties);
        btnComments = findViewById(R.id.btn_DetailActivity_comments);
        imvcart = findViewById(R.id.imv_DetailActivity_cart);
        imvclose = findViewById(R.id.imv_DetailActivity_close);
        imvaddtofav = findViewById(R.id.imv_DetailActivity_addtofav);
        ratingBar = findViewById(R.id.rb_DetailActivity_points);
        slider = findViewById(R.id.sd_DetailActivity_images);
        txtname = findViewById(R.id.txt_DetailActivity_name);
        txtexplan = findViewById(R.id.txt_DetailActivity_infos);
        txtprice = findViewById(R.id.txt_DetailActivity_price);
        txtpprice = findViewById(R.id.txt_DetailActivity_pprice);
        txtTryAgain = findViewById(R.id.txt_DetailActivity_tryagain);
        progressBar = findViewById(R.id.pb_DetailActivity_loading);
        pbCart = findViewById(R.id.pb_DetailActivity_btnCartLoading);
        relativeLayout = findViewById(R.id.rvlayout_DetailActivity_parent);
        linearLayout = findViewById(R.id.linlay_DetailActivity);
        cvToolbar = findViewById(R.id.cv_DetailActivity_toolbar);
        nestedScrollView = findViewById(R.id.scv_DetailActivity);
        toolbar = findViewById(R.id.tb_DetailActivity);
        txtcart = findViewById(R.id.txt_DetailActivity_addtocart);

        txtTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                getDetails();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnadd.setVisibility(View.GONE);
                pbCart.setVisibility(View.VISIBLE);
                detailViewModel.addToBasket(id, phone).observeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<MessageModel>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onSuccess(@NonNull MessageModel messageModel) {

                                if (messageModel.getStatus().equals("success")) {
                                    pbCart.setVisibility(View.GONE);
                                    txtcart.setVisibility(View.VISIBLE);
                                    Toast.makeText(DetailActivity.this, "محصول به سبد خرید اضافه شد!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(DetailActivity.this, "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Toast.makeText(DetailActivity.this, "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();
                                Log.e("ACE", "onError: " + e.toString());
                            }
                        });


            }
        });

        imvaddtofav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getDrawableId(imvaddtofav) == 1) {
                    imvaddtofav.setImageResource(R.drawable.ic_baseline_favorite_24);
                    imvaddtofav.setTag(2);

                    detailViewModel.addToFavorList(phone, id).observeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<MessageModel>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    compositeDisposable.add(d);
                                }

                                @Override
                                public void onSuccess(@NonNull MessageModel messageModel) {

                                    if (messageModel.getMessage().equals("s")) {

                                        Toast.makeText(DetailActivity.this, "محصول با موفقیت به لیست علاقه مندی ها اضافه شد!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(DetailActivity.this, "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Toast.makeText(DetailActivity.this, "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();

                                    Log.e("ACE", "onError: " + e);
                                }
                            });

                } else {

                    DeleteFavoDailog deleteFavoDailog = new DeleteFavoDailog();
                    deleteFavoDailog.setOnDeleteDialogFavorClick(new DeleteFavoDailog.OnDeleteDialogFavorClick() {
                        @Override
                        public void onClick(String detre) {
                            if (detre.equals("Y")) {
                                detailViewModel.deleteFromFav(id, phone).observeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new SingleObserver<MessageModel>() {
                                            @Override
                                            public void onSubscribe(@NonNull Disposable d) {
                                                compositeDisposable.add(d);
                                            }

                                            @Override
                                            public void onSuccess(@NonNull MessageModel messageModel) {


                                                if (messageModel.getStatus().equals("success")) {
                                                    deleteFavoDailog.dismiss();
                                                    imvaddtofav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                                                    imvaddtofav.setTag(1);
                                                    Toast.makeText(DetailActivity.this, "محصول با موفقیت از علافه مندی ها حذف شد!", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(DetailActivity.this, "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onError(@NonNull Throwable e) {
                                                Toast.makeText(DetailActivity.this, "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();

                                                deleteFavoDailog.dismiss();
                                                Log.e("ACE", "onError: " + e.toString());
                                            }
                                        });
                            } else {
                                deleteFavoDailog.dismiss();
                            }
                        }
                    });

                    deleteFavoDailog.show(getSupportFragmentManager(), "");
                }
            }
        });

        imvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnproperties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, PropertiesActivity.class);
                startActivity(intent);

            }
        });

        imvcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.putExtra("flag_cart", 10001);
                startActivity(intent);
            }
        });
        btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailActivity.this, CommentActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", sName);
                startActivity(intent);
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                        Log.i("ACE", "onScrollChange: " + scrollY);
                        if (scrollY >= 0 && scrollY < 100) {
                            trancyToolbar = 0;
                            trancyScrollView = 0;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    nestedScrollView.setTranslationY(trancyScrollView);
                                    toolbar.setTranslationY(trancyToolbar);
                                }
                            });

                        } else if (scrollY > 850) {
                            trancyToolbar = -138;
                            trancyScrollView = -138;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    nestedScrollView.setTranslationY(trancyScrollView);
                                    toolbar.setTranslationY(trancyToolbar);
                                }
                            });

                        } else if (scrollY > oldScrollY) {
                            if (trancyToolbar > -132) {
                                trancyToolbar = trancyToolbar - 5;

                                trancyScrollView = trancyScrollView - 5;


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        nestedScrollView.setTranslationY(trancyScrollView);
                                        toolbar.setTranslationY(trancyToolbar);
                                    }
                                });
                            }
                        } else if (scrollY < oldScrollY) {
                            if (trancyToolbar < 0) {
                                trancyToolbar = trancyToolbar + 15;
                                trancyScrollView = trancyScrollView + 15;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        toolbar.setTranslationY(trancyToolbar);
                                        nestedScrollView.setTranslationY(trancyScrollView);

                                    }
                                });
                            }
                        }
                    }
                });

            }
        }).start();
    }


    private void getDetails() {
        detailViewModel.getDetails(id, phone).observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Detail>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<Detail> details) {

                        Detail detail = details.get(0);
                        List<String> stringList = new ArrayList<>();
                        stringList.add(detail.getImage());
                        slider.setAdapter(new SliderAdapter(stringList));
                        txtname.setText(detail.getTitle());
                        sName = detail.getTitle();
                        txtexplan.setText(Html.fromHtml(detail.getIntroduction()));
                        txtpprice.setText(detail.getPprice());
                        txtprice.setText(detail.getPrice());
                        ratingBar.setRating(Float.parseFloat(detail.getRating()));

                        if (detail.getFav().size() != 0) {
                            imvaddtofav.setImageResource(R.drawable.ic_baseline_favorite_24);
                            imvaddtofav.setTag(2);
                        }

                        if (detail.getBas().size() != 0) {
                            btnadd.setVisibility(View.GONE);
                            txtcart.setVisibility(View.VISIBLE);

                        }
                        relativeLayout.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        progressBar.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                        Toast.makeText(DetailActivity.this, "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();
                        Log.e("ACE", "onError: " + e.toString());
                    }
                });

    }


    private int getDrawableId(ImageView iv) {
        return Integer.parseInt(String.valueOf(iv.getTag()));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}