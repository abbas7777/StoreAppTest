package ir.ace.storeapptest.Search;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Product;

public class SearchViewModel {
    SearchRepository searchRepository=new SearchRepository();
    public Single<List<Product>> getSearchProduct(String sSearch) {
        return searchRepository.getSearchProduct(sSearch);
    }
}
