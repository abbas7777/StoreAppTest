package ir.ace.storeapptest.FrHome;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.Banner;
import ir.ace.storeapptest.Models.CatsItem;
import ir.ace.storeapptest.Models.NewProduct;
import ir.ace.storeapptest.Models.Product;

public class HomeViewModel {
    HomeRepository homeRepository = new HomeRepository();

    public Single<List<CatsItem>> getCats() {
        return homeRepository.getCats();
    }

    public Single<List<Product>> getProduct() {
        return homeRepository.getProduct();
    }

    public Single<List<NewProduct>> getNewProduct() {
        return homeRepository.getNewProduct();
    }

    public Single<List<Banner>> getBanner() {
        return homeRepository.getBanner();
    }
}
