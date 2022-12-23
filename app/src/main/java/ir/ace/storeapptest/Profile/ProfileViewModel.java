package ir.ace.storeapptest.Profile;

import android.content.Context;

import ir.ace.storeapptest.SharePerf.SharePeDataُService;

public class ProfileViewModel {
    ProfileRepository profileRepository=new ProfileRepository();

    public String getPhone(Context context) {
        return profileRepository.getPhone(context);
    }

    public String getName(Context context) {
        return profileRepository.getName(context);
    }

    public void deleteSherP(Context context) {
        profileRepository.deleteSherP(context);
    }
}
