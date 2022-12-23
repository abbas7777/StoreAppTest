package ir.ace.storeapptest.FrHome;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Banner;
import ir.ace.storeapptest.Models.CatsItem;
import ir.ace.storeapptest.Models.NewProduct;
import ir.ace.storeapptest.Models.Product;

public class HomeRepository implements HomeDataSource {
    HomeApiDataSource homeApiDataSource=new HomeApiDataSource();
    @Override
    public Single<List<CatsItem>> getCats() {
        return homeApiDataSource.getCats();
    }

    @Override
    public Single<List<Product>> getProduct() {
        return homeApiDataSource.getProduct();
    }

    @Override
    public Single<List<NewProduct>> getNewProduct() {
        return homeApiDataSource.getNewProduct();
    }

    @Override
    public Single<List<Banner>> getBanner() {
        return homeApiDataSource.getBanner();
    }
}
