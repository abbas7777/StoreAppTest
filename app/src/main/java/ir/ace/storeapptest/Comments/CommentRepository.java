package ir.ace.storeapptest.Comments;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.CommentModel;
import ir.ace.storeapptest.Models.MessageModel;

public class CommentRepository implements CommentDataSource {
    CommentApiService commentApiService = new CommentApiService();

    @Override
    public Single<List<CommentModel>> getComments(String id) {
        return commentApiService.getComments(id);
    }

    @Override
    public Single<MessageModel> setLike(String id) {
        return commentApiService.setLike(id);
    }

    @Override
    public Single<MessageModel> setDissLike(String id) {
        return commentApiService.setDissLike(id);
    }
}
