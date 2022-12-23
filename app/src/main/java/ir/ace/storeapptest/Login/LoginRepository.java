package ir.ace.storeapptest.Login;

import io.reactivex.rxjava3.core.Single;

public class LoginRepository implements LoginDataSource{

    LoginApiDataSource loginApiDataSource=new LoginApiDataSource();
    @Override
    public Single<String> checkLoginUser(String phone, String pass) {
        return loginApiDataSource.checkLoginUser(phone,pass);
    }
}
