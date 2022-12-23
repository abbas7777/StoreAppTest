package ir.ace.storeapptest.Cart;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.BasketModel;
import ir.ace.storeapptest.Models.MessageModel;

public interface CartDataSource {

    Single<List<BasketModel>> getBasketItems(String phone);

    String getPhone(Context context);

    Single<MessageModel> deleteFromBasket(String id);
}
