package ir.ace.storeapptest.SharePerf;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Login.LoginDataSource;

public class SharePeDataُService {
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public SharePeDataُService(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("ACE", 0);
        editor = pref.edit();
    }


    public void saveLoginInfos(String phone, String name) {

        editor.putString("name", name);
        editor.putString("phone", phone);
        editor.commit();
    }

    public String getPhone() {
        return pref.getString("phone", "");
    }

    public String getName() {
        return pref.getString("name", "");
    }

    public void updateName(String name){
        editor.putString("name", name);
        editor.apply();
    }

    public void deleteSherKey(){
        editor.clear().commit();
    }
}
