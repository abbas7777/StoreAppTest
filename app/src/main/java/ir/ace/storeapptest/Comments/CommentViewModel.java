package ir.ace.storeapptest.Comments;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.Models.CommentModel;
import ir.ace.storeapptest.Models.MessageModel;

public class CommentViewModel {
    CommentRepository commentRepository = new CommentRepository();

    public Single<List<CommentModel>> getComments(String id) {
        return commentRepository.getComments(id);
    }
    public Single<MessageModel> setLike(String id) {
        return commentRepository.setLike(id);
    }

    public Single<MessageModel> setDissLike(String id) {
        return commentRepository.setDissLike(id);
    }
}
