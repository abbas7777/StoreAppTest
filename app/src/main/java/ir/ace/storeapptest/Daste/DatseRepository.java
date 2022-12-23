package ir.ace.storeapptest.Daste;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.CatsItem;

public class DatseRepository implements DatseDataSource{
    DatseApiDataSource datseApiDataSource=new DatseApiDataSource();

    @Override
    public Single<List<CatsItem>> getCats() {
        return datseApiDataSource.getCats();
    }
}
