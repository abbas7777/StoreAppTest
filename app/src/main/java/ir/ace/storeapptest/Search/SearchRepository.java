package ir.ace.storeapptest.Search;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Product;

public class SearchRepository implements SearchDataSource{
    SearchApiDataSource searchApiDataSource=new SearchApiDataSource();
    @Override
    public Single<List<Product>> getSearchProduct(String sSearch) {
        return searchApiDataSource.getSearchProduct(sSearch);
    }
}
