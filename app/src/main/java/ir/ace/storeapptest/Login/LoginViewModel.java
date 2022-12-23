package ir.ace.storeapptest.Login;

import io.reactivex.rxjava3.core.Single;

public class LoginViewModel {

    LoginRepository loginRepository=new LoginRepository();
    public Single<String> checkLoginUser(String phone , String pass){
        return loginRepository.checkLoginUser(phone, pass);
    }
}
