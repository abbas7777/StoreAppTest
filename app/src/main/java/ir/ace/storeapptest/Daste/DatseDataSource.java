package ir.ace.storeapptest.Daste;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.CatsItem;

public interface DatseDataSource {

    Single<List<CatsItem>> getCats();
}
