package ir.ace.storeapptest.Profile.AccountInfos;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.ProfileModel;
import ir.ace.storeapptest.SharePerf.SharePeDataُService;

public class AccountInfosSharePerfDataSource implements AccountInfosDataSource{

    @Override
    public Single<String> updateInfos(String name, String phone, String address, String codeposti) {
        return null;
    }

    @Override
    public String getName(Context context) {
        SharePeDataُService sharePeDataُService=new SharePeDataُService(context);

        return sharePeDataُService.getName();
    }



    @Override
    public String getPhone(Context context) {
        SharePeDataُService sharePeDataُService=new SharePeDataُService(context);

        return sharePeDataُService.getPhone();
    }

    @Override
    public Single<List<ProfileModel>> getProfileInfos(String phone) {
        return null;
    }
}
