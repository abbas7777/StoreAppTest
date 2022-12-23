package ir.ace.storeapptest.Regster;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;

public interface RegesterDataSource {

  Single<MessageModel> regester(String name, String phone, String pass);
}
