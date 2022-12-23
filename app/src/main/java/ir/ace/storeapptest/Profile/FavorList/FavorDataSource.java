package ir.ace.storeapptest.Profile.FavorList;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.Models.Product;

public interface FavorDataSource {

    Single<List<Product>> getFavotites(String id);

    Single<MessageModel> deletFavorit(String idp,String phone);
}
