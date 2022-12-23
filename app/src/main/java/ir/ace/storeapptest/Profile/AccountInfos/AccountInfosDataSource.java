package ir.ace.storeapptest.Profile.AccountInfos;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.Models.ProfileModel;

public interface AccountInfosDataSource {

    Single<String> updateInfos(String name,String phone ,String address,String codeposti);

    String getName(Context context);

    String getPhone(Context context);

    Single<List<ProfileModel>> getProfileInfos(String phone);

}
