package ir.ace.storeapptest.Daste.EnLayout;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.Models.ValuesItem;

public class EnLayoutApiDataSource implements EnLayoutDataSource {
    @Override
    public Single<List<Product>> getSortProduct(String cat, int sort) {
        return ApiProvider.apiService.getSortProduct(cat, sort);
    }

    @Override
    public Single<List<Product>> getFilterList(String cat) {
        return ApiProvider.apiService.getFilterList(cat);
    }

    @Override
    public Single<List<Product>> getFilterProduct(List<ValuesItem> valuesFilterList) {
        return ApiProvider.apiService.getFilterProduct(valuesFilterList);
    }
}
