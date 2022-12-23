package ir.ace.storeapptest.Profile.AccountInfos;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.Models.ProfileModel;

public class AccountInfosApiDataSource implements AccountInfosDataSource {

    @Override
    public Single<String> updateInfos(String name, String phone, String address, String codeposti) {
        return ApiProvider.apiService.updateProfileInfos(name, phone, address, codeposti);
    }

    @Override
    public String getName(Context context) {
        return null;
    }

    @Override
    public String getPhone(Context context) {
        return null;
    }

    @Override
    public Single<List<ProfileModel>> getProfileInfos(String phone) {
        return ApiProvider.apiService.getProfile(phone);
    }
}
