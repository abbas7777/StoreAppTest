package ir.ace.storeapptest.Login;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;

public class LoginApiDataSource implements LoginDataSource{

    @Override
    public Single<String> checkLoginUser(String phone, String pass) {
        return ApiProvider.apiService.checkLoginUser(phone,pass);
    }
}
