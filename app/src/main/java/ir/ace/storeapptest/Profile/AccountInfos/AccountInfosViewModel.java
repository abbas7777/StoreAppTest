package ir.ace.storeapptest.Profile.AccountInfos;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.Models.ProfileModel;

public class AccountInfosViewModel {
    AccountInfosRepository accountInfosRepository = new AccountInfosRepository();

    public Single<String> updateInfos(String name, String phone, String address, String codeposti) {
        return accountInfosRepository.updateInfos(name, phone, address, codeposti);
    }

    public String getName(Context context) {
        return accountInfosRepository.getName(context);
    }


    public String getPhone(Context context) {
        return accountInfosRepository.getPhone(context);
    }

    public Single<List<ProfileModel>> getProfileInfos(String phone) {
        return accountInfosRepository.getProfileInfos(phone);
    }
}
