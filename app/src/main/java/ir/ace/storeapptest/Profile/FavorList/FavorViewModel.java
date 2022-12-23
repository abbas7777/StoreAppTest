package ir.ace.storeapptest.Profile.FavorList;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.Models.Product;

public class FavorViewModel {

    FavorRepository favorRepository=new FavorRepository();

    public Single<List<Product>> getFavotites(String id) {
        return favorRepository.getFavotites(id);
    }

    public Single<MessageModel> deletFavorit(String idp, String phone) {
        return favorRepository.deletFavorit(idp,phone);
    }
}
