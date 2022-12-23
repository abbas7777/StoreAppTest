package ir.ace.storeapptest.Properties;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.PropertiesItem;

public interface PropertiesDataSource {

    Single<List<PropertiesItem>> getProperties();
}
