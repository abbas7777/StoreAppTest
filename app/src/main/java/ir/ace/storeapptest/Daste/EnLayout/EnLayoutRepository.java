package ir.ace.storeapptest.Daste.EnLayout;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.Models.ValuesItem;

public class EnLayoutRepository implements EnLayoutDataSource{
    EnLayoutApiDataSource enLayoutApiDataSource=new EnLayoutApiDataSource();

    @Override
    public Single<List<Product>> getSortProduct(String cat, int sort) {
        return enLayoutApiDataSource.getSortProduct(cat,sort);
    }

    @Override
    public Single<List<Product>> getFilterList(String cat) {
        return enLayoutApiDataSource.getFilterList(cat);
    }

    @Override
    public Single<List<Product>> getFilterProduct(List<ValuesItem> valuesFilterList) {
        return enLayoutApiDataSource.getFilterProduct(valuesFilterList);
    }
}
