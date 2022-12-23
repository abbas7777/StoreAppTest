package ir.ace.storeapptest.Daste.EnLayout;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.Models.ValuesItem;

public class EnLayoutViewModel {
    EnLayoutRepository enLayoutRepository = new EnLayoutRepository();

    public Single<List<Product>> getCatProduct(String cat, int sort) {
        return enLayoutRepository.getSortProduct(cat,sort);
    }

    public Single<List<Product>> getFilterProduct(String cat) {
        return enLayoutRepository.getFilterList(cat);
    }

    public Single<List<Product>> getFilterProduct(List<ValuesItem> valuesFilterList) {
        return enLayoutRepository.getFilterProduct(valuesFilterList);
    }
}
