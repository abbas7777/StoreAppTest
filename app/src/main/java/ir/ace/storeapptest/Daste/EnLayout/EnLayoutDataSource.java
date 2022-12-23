package ir.ace.storeapptest.Daste.EnLayout;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.Models.ValuesItem;

public interface EnLayoutDataSource {

    Single<List<Product>> getSortProduct(String cat, int sort);
    Single<List<Product>> getFilterList(String cat);
    Single<List<Product>> getFilterProduct(List<ValuesItem> valuesFilterList);
}
