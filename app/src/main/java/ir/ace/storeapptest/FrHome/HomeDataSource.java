package ir.ace.storeapptest.FrHome;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Banner;
import ir.ace.storeapptest.Models.CatsItem;
import ir.ace.storeapptest.Models.NewProduct;
import ir.ace.storeapptest.Models.Product;

public interface HomeDataSource {

    Single<List<CatsItem>> getCats();
    Single<List<Product>> getProduct();
    Single<List<NewProduct>> getNewProduct();
    Single<List<Banner>> getBanner();
}
