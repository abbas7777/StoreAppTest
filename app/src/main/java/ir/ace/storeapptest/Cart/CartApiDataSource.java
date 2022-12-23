package ir.ace.storeapptest.Cart;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.BasketModel;
import ir.ace.storeapptest.Models.MessageModel;

public class CartApiDataSource implements CartDataSource{
    @Override
    public Single<List<BasketModel>> getBasketItems(String phone) {
        return ApiProvider.apiService.getBasketList(phone);
    }

    @Override
    public String getPhone(Context context) {
        return null;
    }

    @Override
    public Single<MessageModel> deleteFromBasket(String id) {
        return ApiProvider.apiService.deleteFromBasket(id);
    }
}
