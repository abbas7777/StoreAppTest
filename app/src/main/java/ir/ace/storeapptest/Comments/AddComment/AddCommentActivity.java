package ir.ace.storeapptest.Comments.AddComment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.ImageReader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.R;

public class AddCommentActivity extends AppCompatActivity {

    String id, name,nameUser;
    String suggestedValue = "";
    Button btnSugessted, btnUnSuggested, btnSumbit;
    EditText etxtTitle, etxtPassage;
    ImageView imvClose;
    TextView txtTitle;
    AddCommentViewModel addCommentViewModel = new AddCommentViewModel();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        setUpViews();
    }

    private void setUpViews() {
        nameUser=addCommentViewModel.getNameUser(AddCommentActivity.this);
        id = getIntent().getExtras().getString("idp");
        name = getIntent().getExtras().getString("name");
        btnSugessted = findViewById(R.id.btn_AddCommentActivity_sugessted);
        btnUnSuggested = findViewById(R.id.btn_AddCommentActivity_unsugessted);
        btnSumbit = findViewById(R.id.btn_AddCommentActivity_sumbit);
        etxtTitle = findViewById(R.id.etxt_AddCommentActivity_title);
        etxtPassage = findViewById(R.id.etxt_AddCommentActivity_passage);
        imvClose = findViewById(R.id.imv_AddCommentActivity_close);
        txtTitle = findViewById(R.id.txt_AddCommentActivity_name);

        txtTitle.setText(name);

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(AddCommentActivity.this,R.style.Theme_Dialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                dialog.setContentView(R.layout.dialog_loading);
                dialog.show();
                if (suggestedValue.equals("")) {

                    Toast.makeText(AddCommentActivity.this, "قسمت پیشنهاد به دیگران نمی تواند خالی باشد!", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                } else if (etxtTitle.getText().toString().equals("")) {

                    Toast.makeText(AddCommentActivity.this, "عنوان نظر خود را تعیین کنید!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                } else if (etxtPassage.getText().toString().equals("")) {

                    Toast.makeText(AddCommentActivity.this, "متن نظر نمی تواند خالی باشد!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                } else {

                    addCommentViewModel.sendComment(nameUser, id, etxtTitle.getText().toString(), etxtPassage.getText().toString(), suggestedValue)
                            .observeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<MessageModel>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    compositeDisposable.add(d);
                                }

                                @Override
                                public void onSuccess(@NonNull MessageModel messageModel) {

                                    Toast.makeText(AddCommentActivity.this, messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }

                                @Override
                                public void onError(@NonNull Throwable e) {

                                    Log.e("ACE", "onError: " + e);
                                    dialog.dismiss();

                                }
                            });
                }
            }
        });
        btnSugessted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getButtonId(btnSugessted) == 10001) {


                    DrawableCompat.setTint(btnSugessted.getCompoundDrawables()[1], ContextCompat.getColor(AddCommentActivity.this, R.color.teal_700));
                    btnSugessted.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.teal_700));
                    DrawableCompat.setTint(btnUnSuggested.getCompoundDrawables()[1], ContextCompat.getColor(AddCommentActivity.this, R.color.gray_300));
                    btnUnSuggested.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray_300));
                    btnSugessted.setTag("10003");
                    btnUnSuggested.setTag("10005");
                    suggestedValue = "1";

                } else {
                    DrawableCompat.setTint(btnSugessted.getCompoundDrawables()[1], ContextCompat.getColor(AddCommentActivity.this, R.color.gray_300));
                    btnSugessted.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray_300));
                    btnSugessted.setTag("10001");
                    suggestedValue = "";

                }
            }
        });

        btnUnSuggested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getButtonId(btnUnSuggested) == 10005) {


                    DrawableCompat.setTint(btnUnSuggested.getCompoundDrawables()[1], ContextCompat.getColor(AddCommentActivity.this, R.color.red_500));
                    btnUnSuggested.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red_500));
                    DrawableCompat.setTint(btnSugessted.getCompoundDrawables()[1], ContextCompat.getColor(AddCommentActivity.this, R.color.gray_300));
                    btnSugessted.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray_300));
                    btnUnSuggested.setTag("10006");
                    btnSugessted.setTag("10001");
                    suggestedValue = "0";
                } else {
                    DrawableCompat.setTint(btnUnSuggested.getCompoundDrawables()[1], ContextCompat.getColor(AddCommentActivity.this, R.color.gray_300));
                    btnUnSuggested.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray_300));
                    btnUnSuggested.setTag("10005");
                    suggestedValue = "";

                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    private int getButtonId(Button iv) {
        return Integer.parseInt(String.valueOf(iv.getTag()));
    }
}