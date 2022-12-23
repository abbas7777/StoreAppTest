package ir.ace.storeapptest.Comments.AddComment;

import android.content.Context;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.MessageModel;

public interface AddCommentDataSource {

    Single<MessageModel> sendComment( String user,String idp,String title, String passage, String suggested);
    String getNameUser(Context context);
}
