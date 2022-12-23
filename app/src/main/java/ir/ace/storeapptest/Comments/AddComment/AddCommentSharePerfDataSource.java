package ir.ace.storeapptest.Comments.AddComment;

import android.content.Context;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.SharePerf.SharePeDataُService;

public class AddCommentSharePerfDataSource implements AddCommentDataSource {
    @Override
    public Single<MessageModel> sendComment(String idp, String user, String suggested, String title, String passage) {
        return null;
    }

    @Override
    public String getNameUser(Context context) {
        SharePeDataُService sharePeDataُService=new SharePeDataُService(context);
        return sharePeDataُService.getName();
    }
}
