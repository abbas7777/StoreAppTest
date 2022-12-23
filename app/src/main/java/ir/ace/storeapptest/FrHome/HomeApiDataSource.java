package ir.ace.storeapptest.FrHome;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.Banner;
import ir.ace.storeapptest.Models.CatsItem;
import ir.ace.storeapptest.Models.NewProduct;
import ir.ace.storeapptest.Models.Product;

public class HomeApiDataSource implements HomeDataSource{
    @Override
    public Single<List<CatsItem>> getCats() {
        return ApiProvider.apiService().getCats();
    }

    @Override
    public Single<List<Product>> getProduct() {
        return ApiProvider.apiService().getProduct();
    }

    @Override
    public Single<List<NewProduct>> getNewProduct() {
        return ApiProvider.apiService.getNewProduct();
    }

    @Override
    public Single<List<Banner>> getBanner() {
        return ApiProvider.apiService().getBanner();
    }

}
