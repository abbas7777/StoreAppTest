package ir.ace.storeapptest.Detail;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.Detail;
import ir.ace.storeapptest.Models.MessageModel;

public class DetailApiDataSource implements DetailDataSource{
    @Override
    public Single<List<Detail>> getDetails(String id, String phone) {
        return ApiProvider.apiService.getDetails(id, phone);
    }

    @Override
    public String getPhone(Context context) {
        return null;
    }

    @Override
    public Single<MessageModel> addToFavorList(String phone, String idp) {
        return ApiProvider.apiService.addToFoverList(phone, idp);
    }

    @Override
    public Single<MessageModel> deleteFromFav(String idp, String phone) {
        return ApiProvider.apiService().deleteFromFav(idp, phone);
    }

    @Override
    public Single<MessageModel> addToBasket(String id, String phone) {
        return ApiProvider.apiService.addToBasket(id, phone);
    }
}
