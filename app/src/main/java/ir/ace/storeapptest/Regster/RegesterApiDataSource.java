package ir.ace.storeapptest.Regster;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.MessageModel;

public class RegesterApiDataSource implements RegesterDataSource{
    @Override
    public Single<MessageModel> regester(String name, String phone, String pass) {
        return ApiProvider.apiService.signUp(name, phone, pass);
    }
}
