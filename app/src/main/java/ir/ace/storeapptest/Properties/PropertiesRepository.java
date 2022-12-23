package ir.ace.storeapptest.Properties;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.PropertiesItem;

public class PropertiesRepository implements PropertiesDataSource{
    PropertiesApiService propertiesApiService=new PropertiesApiService();
    @Override
    public Single<List<PropertiesItem>> getProperties() {
        return propertiesApiService.getProperties();
    }
}
