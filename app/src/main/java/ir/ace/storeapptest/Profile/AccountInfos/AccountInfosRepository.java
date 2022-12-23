package ir.ace.storeapptest.Profile.AccountInfos;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.Models.ProfileModel;

public class AccountInfosRepository implements AccountInfosDataSource {

    AccountInfosApiDataSource accountInfosApiDataSource=new AccountInfosApiDataSource();
    AccountInfosSharePerfDataSource accountInfosSharePerfDataSource=new AccountInfosSharePerfDataSource();
    @Override
    public Single<String> updateInfos(String name, String phone, String address, String codeposti) {
        return accountInfosApiDataSource.updateInfos(name, phone, address, codeposti);
    }

    @Override
    public String getName(Context context) {
        return accountInfosSharePerfDataSource.getName(context);
    }

    @Override
    public String getPhone(Context context) {
        return accountInfosSharePerfDataSource.getPhone(context);
    }

    @Override
    public Single<List<ProfileModel>> getProfileInfos(String phone) {
        return accountInfosApiDataSource.getProfileInfos(phone);
    }
}
