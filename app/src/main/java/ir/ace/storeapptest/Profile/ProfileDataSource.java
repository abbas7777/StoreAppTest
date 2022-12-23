package ir.ace.storeapptest.Profile;

import android.content.Context;

public interface ProfileDataSource {

    String getPhone(Context context);

    String getName(Context context);

    void deleteSherP(Context context);


}
