package ir.ace.storeapptest.Comments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.ace.storeapptest.Comments.AddComment.AddCommentActivity;
import ir.ace.storeapptest.Models.CommentModel;
import ir.ace.storeapptest.Models.MessageModel;
import ir.ace.storeapptest.R;

public class CommentActivity extends AppCompatActivity {

    ImageView imvClose;
    RecyclerView rvComments;
    String id;
    CommentViewModel viewModel = new CommentViewModel();
    CompositeDisposable compositeDisposable=new CompositeDisposable();
   RvCommentsAdapter rvCommentsAdapter;
   FloatingActionButton fabAddComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        setUpViews();
        getComments();
    }


    private void setUpViews() {
        id=getIntent().getExtras().getString("id");
        imvClose=findViewById(R.id.imv_CommentActivity_close);
        rvComments=findViewById(R.id.rv_CommentActivity_comments);
        fabAddComment=findViewById(R.id.fab_CommentActivity_addComments);

        fabAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(), AddCommentActivity.class);
                intent.putExtra("idp",id);
                intent.putExtra("name",getIntent().getExtras().getString("name"));
                startActivity(intent);
            }
        });
        rvComments.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
    }


    private void getComments() {
        viewModel.getComments(id).observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<CommentModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<CommentModel> commentModels) {

                        rvCommentsAdapter=new RvCommentsAdapter(getApplicationContext(), commentModels, new RvCommentsAdapter.OnClick() {
                            @Override
                            public void setLike(CommentModel commentModel) {
                                viewModel.setLike(commentModel.getId()).observeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new SingleObserver<MessageModel>() {
                                            @Override
                                            public void onSubscribe(@NonNull Disposable d) {
                                                compositeDisposable.add(d);
                                            }

                                            @Override
                                            public void onSuccess(@NonNull MessageModel messageModel) {

                                                rvCommentsAdapter.changeLikeViews(commentModel,"like");
                                            }

                                            @Override
                                            public void onError(@NonNull Throwable e) {
                                                Log.e("ACE", "onError: "+e );
                                            }
                                        });
                            }

                            @Override
                            public void setDissLike(CommentModel commentModel) {

                                viewModel.setDissLike(commentModel.getId()).observeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new SingleObserver<MessageModel>() {
                                            @Override
                                            public void onSubscribe(@NonNull Disposable d) {
                                                compositeDisposable.add(d);

                                            }

                                            @Override
                                            public void onSuccess(@NonNull MessageModel messageModel) {
                                                rvCommentsAdapter.changeLikeViews(commentModel,"disslike");

                                            }

                                            @Override
                                            public void onError(@NonNull Throwable e) {
                                                Log.e("ACE", "onError: "+e );

                                            }
                                        });
                            }
                        });
                        rvComments.setAdapter(rvCommentsAdapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("ACE", "onError: "+e.toString() );
                    }
                });
    }
}