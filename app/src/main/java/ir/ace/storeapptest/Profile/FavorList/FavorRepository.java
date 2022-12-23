package ir.ace.storeapptest.Profile.FavorList;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.Models.Product;

public class FavorRepository implements FavorDataSource{
    FavorApiDataSource favorApiDataSource=new FavorApiDataSource();
    @Override
    public Single<List<Product>> getFavotites(String id) {
        return favorApiDataSource.getFavotites(id);
    }

    @Override
    public Single<MessageModel> deletFavorit(String idp, String phone) {
        return favorApiDataSource.deletFavorit(idp,phone);
    }
}
