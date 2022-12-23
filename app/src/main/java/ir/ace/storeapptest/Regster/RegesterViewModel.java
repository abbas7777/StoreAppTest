package ir.ace.storeapptest.Regster;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;

public class RegesterViewModel {
    RegesterRepository regesterRepository=new RegesterRepository();

    public Single<MessageModel> regester(String name, String phone, String pass) {
        return regesterRepository.regester(name, phone, pass);
    }
}
