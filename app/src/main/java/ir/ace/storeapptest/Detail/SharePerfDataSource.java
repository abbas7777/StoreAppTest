package ir.ace.storeapptest.Detail;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Detail;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.SharePerf.SharePeDataُService;

public class SharePerfDataSource implements DetailDataSource{
    @Override
    public Single<List<Detail>> getDetails(String id, String phone) {
        return null;
    }

    @Override
    public String getPhone(Context context) {
        SharePeDataُService sharePeDataُService=new SharePeDataُService(context);

        return sharePeDataُService.getPhone() ;
    }

    @Override
    public Single<MessageModel> addToFavorList(String phone, String idp) {
        return null;
    }

    @Override
    public Single<MessageModel> deleteFromFav(String idp, String phone) {
        return null;
    }

    @Override
    public Single<MessageModel> addToBasket(String id, String phone) {
        return null;
    }
}
