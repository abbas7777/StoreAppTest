package ir.ace.storeapptest.Comments;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.CommentModel;
import ir.ace.storeapptest.Models.MessageModel;

public interface CommentDataSource {

    Single<List<CommentModel>> getComments(String id);
    Single<MessageModel> setLike(String id);
    Single<MessageModel> setDissLike(String id);
}
