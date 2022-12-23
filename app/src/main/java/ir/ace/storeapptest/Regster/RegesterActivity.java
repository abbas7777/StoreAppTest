package ir.ace.storeapptest.Regster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Login.LoginActivity;
import ir.ace.storeapptest.Main.MainActivity;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.R;
import ir.ace.storeapptest.SharePerf.SharePeDataُService;

public class RegesterActivity extends AppCompatActivity {

    TextInputEditText etxtnamel, etxtphone, etxtpass;
    Button btnsumbit;
    CheckBox chblaws;
    ImageView imvShowPass;
    RegesterViewModel regesterViewModel = new RegesterViewModel();
    CompositeDisposable cd = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);

        setUpViews();
    }

    private void setUpViews() {
        etxtnamel = findViewById(R.id.etxt_RegesterActivity_name);
        etxtphone = findViewById(R.id.etxt_RegesterActivity_phone);
        etxtpass = findViewById(R.id.etxt_RegesterActivity_password);
        chblaws = findViewById(R.id.chb_RegesterActivity_laws);
        btnsumbit = findViewById(R.id.btn_regester_sumbit);
        imvShowPass = findViewById(R.id.imv_RegesterActivity_showpass);

        chblaws.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                btnsumbit.setEnabled(isChecked);
            }
        });

        imvShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imvShowPass.getId() == R.id.imv_RegesterActivity_showpass) {

                    if (etxtpass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        ((ImageView) (imvShowPass)).setImageResource(R.drawable.ic_visibility_off_black_18dp);

                        //Show Password
                        etxtpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        ((ImageView) (imvShowPass)).setImageResource(R.drawable.ic_visibility_black_24dp);

                        //Hide Password
                        etxtpass.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }
            }
        });

        btnsumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etxtnamel.getText().toString().equals("")) {

                    Toast.makeText(RegesterActivity.this, "نام و نام خانوادگی نمی تواند خالی باشد!", Toast.LENGTH_SHORT).show();
                } else if (etxtphone.getText().toString().equals("")) {

                    Toast.makeText(RegesterActivity.this, "شماره تلفن نمی تواند خالی باشد!", Toast.LENGTH_SHORT).show();


                } else if (etxtpass.getText().toString().equals("")) {

                    Toast.makeText(RegesterActivity.this, "کلمه عبور نمی تواند خالی باشد!", Toast.LENGTH_SHORT).show();


                } else {

                    regesterViewModel.regester(etxtnamel.getText().toString(), etxtphone.getText().toString(), etxtpass.getText().toString())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<MessageModel>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    cd.add(d);
                                }

                                @Override
                                public void onSuccess(@NonNull MessageModel messageModel) {
                                    if (messageModel.getStatus().equals("success")) {

                                        if (messageModel.getMessage().equals("BS")) {
                                            Toast.makeText(RegesterActivity.this, "این شماره تلفن قبلا ثبت شده است!", Toast.LENGTH_SHORT).show();

                                        } else {
                                            SharePeDataُService sharePeDataُService = new SharePeDataُService(RegesterActivity.this);
                                            sharePeDataُService.saveLoginInfos(etxtphone.getText().toString(), etxtnamel.getText().toString());
                                            Intent intent = new Intent(RegesterActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(RegesterActivity.this, "با موفقیت وارد حساب کاربری شدید!", Toast.LENGTH_SHORT).show();
                                            finish();

                                        }
                                    } else {
                                        Toast.makeText(RegesterActivity.this, "مشکل ارتباط با سرور!", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {

                                    Log.e("ACE", "onError: " + e);
                                }
                            });

                }
            }
        });

    }


}