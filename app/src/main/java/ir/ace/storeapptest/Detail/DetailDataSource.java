package ir.ace.storeapptest.Detail;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Detail;
import ir.ace.storeapptest.Models.MessageModel;

public interface DetailDataSource {

    Single<List<Detail>> getDetails(String id,String phone);

    String getPhone(Context context);

    Single<MessageModel> addToFavorList(String phone,String idp);

    Single<MessageModel> deleteFromFav(String idp,String phone);

    Single<MessageModel> addToBasket(String id,String phone);

}
