package ir.ace.storeapptest.Profile;

import android.content.Context;

import ir.ace.storeapptest.SharePerf.SharePeDataُService;

public class ProfileSherPerDataSource implements ProfileDataSource {

    @Override
    public String getPhone(Context context) {
        SharePeDataُService sharePeDataُService = new SharePeDataُService(context);
        return sharePeDataُService.getPhone();
    }

    @Override
    public String getName(Context context) {
        SharePeDataُService sharePeDataُService = new SharePeDataُService(context);
        return sharePeDataُService.getName();
    }

    @Override
    public void deleteSherP(Context context) {
        SharePeDataُService sharePeDataُService = new SharePeDataُService(context);
        sharePeDataُService.deleteSherKey();
    }
}
