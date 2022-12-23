package ir.ace.storeapptest.Cart;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.BasketModel;
import ir.ace.storeapptest.Models.MessageModel;

public class CartViewModel {
    CartRepository cartRepository = new CartRepository();

    public Single<List<BasketModel>> getBasketItems(String phone) {
        return cartRepository.getBasketItems(phone);
    }

    public String getPhone(Context context) {
        return cartRepository.getPhone(context);
    }

    public Single<MessageModel> deleteFromBasket(String id) {
        return cartRepository.deleteFromBasket(id);
    }
}
