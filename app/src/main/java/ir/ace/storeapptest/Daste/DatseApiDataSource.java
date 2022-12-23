package ir.ace.storeapptest.Daste;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.CatsItem;

public class DatseApiDataSource implements DatseDataSource{
    @Override
    public Single<List<CatsItem>> getCats() {
        return ApiProvider.apiService().getCats();
    }
}
