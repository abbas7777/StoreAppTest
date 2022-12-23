package ir.ace.storeapptest.Comments.AddComment;

import android.content.Context;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;

public class AddCommentRepository implements AddCommentDataSource{

    AddCommentApiService commentApiService=new AddCommentApiService();
    AddCommentSharePerfDataSource addCommentSharePerfDataSource=new AddCommentSharePerfDataSource();
    @Override
    public Single<MessageModel> sendComment(String user, String idp, String title, String passage, String suggested) {
        return commentApiService.sendComment(user, idp, title, passage, suggested);
    }

    @Override
    public String getNameUser(Context context) {
        return addCommentSharePerfDataSource.getNameUser(context);
    }
}
