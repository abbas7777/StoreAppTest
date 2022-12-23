package ir.ace.storeapptest.Cart;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.BasketModel;
import ir.ace.storeapptest.Models.MessageModel;

public class CartRepository implements CartDataSource{
    CartApiDataSource cartApiDataSource=new CartApiDataSource();
    CartDataSherPSource cartDataSherPSource=new CartDataSherPSource();

    @Override
    public Single<List<BasketModel>> getBasketItems(String phone) {
        return cartApiDataSource.getBasketItems(phone);
    }

    @Override
    public String getPhone(Context context) {
        return cartDataSherPSource.getPhone(context);
    }

    @Override
    public Single<MessageModel> deleteFromBasket(String id) {
        return cartApiDataSource.deleteFromBasket(id);
    }
}
