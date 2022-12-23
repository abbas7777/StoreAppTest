package ir.ace.storeapptest.Comments.AddComment;

import android.content.Context;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.SharePerf.SharePeDataُService;

public class AddCommentViewModel {
    AddCommentRepository addCommentRepository=new AddCommentRepository();

    public Single<MessageModel> sendComment(String user, String idp, String title, String passage, String suggested) {
        return addCommentRepository.sendComment(user, idp, title, passage, suggested);
    }

    public String getNameUser(Context context) {
        SharePeDataُService sharePeDataُService=new SharePeDataُService(context);
        return addCommentRepository.getNameUser(context);
    }
}
