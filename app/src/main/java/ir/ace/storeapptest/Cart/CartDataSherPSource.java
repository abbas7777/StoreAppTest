package ir.ace.storeapptest.Cart;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.BasketModel;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.SharePerf.SharePeDataُService;

public class CartDataSherPSource implements CartDataSource {

    @Override
    public Single<List<BasketModel>> getBasketItems(String phone) {
        return null;
    }

    @Override
    public String getPhone(Context context) {
        SharePeDataُService sharePeDataُService=new SharePeDataُService(context);

        return sharePeDataُService.getPhone();
    }

    @Override
    public Single<MessageModel> deleteFromBasket(String id) {
        return null;
    }


}
