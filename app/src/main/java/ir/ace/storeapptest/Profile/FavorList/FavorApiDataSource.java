package ir.ace.storeapptest.Profile.FavorList;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.Models.Product;

public class FavorApiDataSource implements FavorDataSource{
    @Override
    public Single<List<Product>> getFavotites(String id) {
        return ApiProvider.apiService.getFavorit(id);
    }

    @Override
    public Single<MessageModel> deletFavorit(String idp, String phone) {
        return ApiProvider.apiService.deleteFromFav(idp, phone);
    }
}
