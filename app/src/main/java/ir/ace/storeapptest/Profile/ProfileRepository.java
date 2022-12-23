package ir.ace.storeapptest.Profile;

import android.content.Context;

public class ProfileRepository implements ProfileDataSource{
    ProfileSherPerDataSource profileSherPerDataSource=new ProfileSherPerDataSource();
    @Override
    public String getPhone(Context context) {
        return profileSherPerDataSource.getPhone(context);
    }

    @Override
    public String getName(Context context) {
        return profileSherPerDataSource.getName(context);
    }

    @Override
    public void deleteSherP(Context context) {
        profileSherPerDataSource.deleteSherP(context);
    }
}
