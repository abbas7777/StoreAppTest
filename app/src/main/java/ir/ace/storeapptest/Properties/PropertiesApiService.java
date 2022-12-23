package ir.ace.storeapptest.Properties;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.PropertiesItem;

public class PropertiesApiService implements PropertiesDataSource{
    @Override
    public Single<List<PropertiesItem>> getProperties() {
        return ApiProvider.apiService.getProperties();
    }
}
