package ir.ace.storeapptest.Search;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Product;

public interface SearchDataSource {

    Single<List<Product>> getSearchProduct(String sSearch);
}
