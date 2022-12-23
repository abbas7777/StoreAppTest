package ir.ace.storeapptest.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Main.MainActivity;
import ir.ace.storeapptest.R;
import ir.ace.storeapptest.SharePerf.SharePeDataُService;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etxtphone, etxtpass;
    Button btnenter;
    LoginViewModel loginViewModel = new LoginViewModel();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpViews();
    }

    private void setUpViews() {
        etxtpass = findViewById(R.id.etxt_LoginActivity_pass);
        etxtphone = findViewById(R.id.etxt_LoginActivity_phone);
        btnenter = findViewById(R.id.btn_LoginActivity_enter);

        btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.checkLoginUser(etxtphone.getText().toString(), etxtpass.getText().toString()).observeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onSuccess(String s) {

                                if (s.equals("N")) {
                                    Toast.makeText(LoginActivity.this, "کلمه عبور یا شماره تلفن ناردست است!", Toast.LENGTH_SHORT).show();
                                } else {

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            SharePeDataُService sharePeDataُService = new SharePeDataُService(LoginActivity.this);
                                            sharePeDataُService.saveLoginInfos(etxtphone.getText().toString(), s);
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }).start();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                                Log.e("ACE", "onError: " + e.toString());
                            }
                        });
            }
        });
    }

    public void ShowHidePass(View view) {

        if (view.getId() == R.id.imv_LoginActivity_showpass) {

            if (etxtpass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_visibility_off_black_18dp);

                //Show Password
                etxtpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_visibility_black_24dp);

                //Hide Password
                etxtpass.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

}