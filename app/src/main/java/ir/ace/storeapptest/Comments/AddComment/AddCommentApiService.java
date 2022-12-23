package ir.ace.storeapptest.Comments.AddComment;

import android.content.Context;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.MessageModel;

public class AddCommentApiService implements AddCommentDataSource{


    @Override
    public Single<MessageModel> sendComment(String user, String idp, String title, String passage, String suggested) {
        return ApiProvider.apiService.sendComment(user, idp, title, passage, suggested);
    }

    @Override
    public String getNameUser(Context context) {
        return null;
    }
}
