package ir.ace.storeapptest.Detail;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Detail;
import ir.ace.storeapptest.Models.MessageModel;

public class DetailRepository implements DetailDataSource {

    DetailApiDataSource detailApiDataSource = new DetailApiDataSource();
    SharePerfDataSource sharePerfDataSource = new SharePerfDataSource();

    @Override
    public Single<List<Detail>> getDetails(String id, String phone) {
        return detailApiDataSource.getDetails(id, phone);
    }

    @Override
    public String getPhone(Context context) {
        return sharePerfDataSource.getPhone(context);
    }

    @Override
    public Single<MessageModel> addToFavorList(String phone, String idp) {
        return detailApiDataSource.addToFavorList(phone, idp);
    }

    @Override
    public Single<MessageModel> deleteFromFav(String idp, String phone) {
        return detailApiDataSource.deleteFromFav(idp, phone);
    }

    @Override
    public Single<MessageModel> addToBasket(String id, String phone) {
        return detailApiDataSource.addToBasket(id, phone);
    }
}
