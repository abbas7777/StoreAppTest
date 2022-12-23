package ir.ace.storeapptest.Comments;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ir.ace.storeapptest.DataServer.ApiProvider;
import ir.ace.storeapptest.Models.CommentModel;
import ir.ace.storeapptest.Models.MessageModel;

public class CommentApiService implements CommentDataSource{
    @Override
    public Single<List<CommentModel>> getComments(String id) {
        return ApiProvider.apiService().getComments(id);
    }

    @Override
    public Single<MessageModel> setLike(String id) {
        return ApiProvider.apiService().setLike(id);
    }

    @Override
    public Single<MessageModel> setDissLike(String id) {
        return ApiProvider.apiService().setDissLike(id);
    }
}
