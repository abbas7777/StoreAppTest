package ir.ace.storeapptest.Detail;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Detail;
import ir.ace.storeapptest.Models.MessageModel;

public class DetailViewModel {
    DetailRepository detailRepository = new DetailRepository();

    public Single<List<Detail>> getDetails(String id, String phone) {

        return detailRepository.getDetails(id, phone);
    }

    public String getPhone(Context context) {
        return detailRepository.getPhone(context);
    }

    public Single<MessageModel> addToFavorList(String phone, String idp) {
        return detailRepository.addToFavorList(phone, idp);
    }

    public Single<MessageModel> deleteFromFav(String idp, String phone) {
        return detailRepository.deleteFromFav(idp, phone);
    }

    public Single<MessageModel> addToBasket(String id, String phone) {
        return detailRepository.addToBasket(id, phone);
    }
}
