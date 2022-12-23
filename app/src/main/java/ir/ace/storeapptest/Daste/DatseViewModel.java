package ir.ace.storeapptest.Daste;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.CatsItem;

public class DatseViewModel {
    DatseRepository datseRepository=new DatseRepository();

    public Single<List<CatsItem>> getCats() {
        return datseRepository.getCats();
    }
}
