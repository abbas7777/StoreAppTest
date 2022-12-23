package ir.ace.storeapptest.Regster;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;

public class RegesterRepository implements RegesterDataSource{
    RegesterApiDataSource regesterApiDataSource=new RegesterApiDataSource();

    @Override
    public Single<MessageModel> regester(String name, String phone, String pass) {
        return regesterApiDataSource.regester(name, phone, pass);
    }
}
