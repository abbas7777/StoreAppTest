package ir.ace.storeapptest.Properties;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.PropertiesItem;

public class PropertiesViewModel {
  PropertiesRepository propertiesRepository=new PropertiesRepository();
    public Single<List<PropertiesItem>> getProperties(){
      return  propertiesRepository.getProperties();
    }
}
