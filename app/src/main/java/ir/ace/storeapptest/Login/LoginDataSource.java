package ir.ace.storeapptest.Login;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;

public interface LoginDataSource {

    Single<String> checkLoginUser(String phone, String pass);

}
