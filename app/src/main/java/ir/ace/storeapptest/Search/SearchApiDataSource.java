package ir.ace.storeapptest.Search;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.Product;

public class SearchApiDataSource implements SearchDataSource{
    @Override
    public Single<List<Product>> getSearchProduct(String sSearch) {
        return ApiProvider.apiService.getSearchProduct(sSearch);
    }
}
