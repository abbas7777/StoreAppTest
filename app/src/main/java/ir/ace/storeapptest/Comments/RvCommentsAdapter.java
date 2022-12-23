package ir.ace.storeapptest.Comments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.ace.storeapptest.Models.CommentModel;
import ir.ace.storeapptest.R;

public class RvCommentsAdapter extends RecyclerView.Adapter<RvCommentsAdapter.RvCommentViewHolder> {

    Context context;
    List<CommentModel> commentModelList;
    OnClick onClickLIke;
    public RvCommentsAdapter(Context context, List<CommentModel> commentModelList, OnClick onClickLIke) {
        this.context = context;
        this.commentModelList = commentModelList;
        this.onClickLIke=onClickLIke;
    }

    @NonNull
    @Override
    public RvCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RvCommentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvCommentViewHolder holder, int position) {
        CommentModel commentModel = commentModelList.get(position);

        if (commentModel.getSuggest().equals("1")) {
            holder.txtSuggested.setVisibility(View.VISIBLE);
            holder.txtUnsuggested.setVisibility(View.GONE);


        } else {
            holder.txtUnsuggested.setVisibility(View.VISIBLE);
            holder.txtSuggested.setVisibility(View.GONE);

        }

        holder.txtDesc.setText(Html.fromHtml(commentModel.getPassage()) );
        holder.txtDissLikeCount.setText(commentModel.getDislikecount());
        holder.txtLikeCount.setText(commentModel.getLikecount());
        holder.txtUserName.setText(commentModel.getUser());
        holder.txtTitle.setText(commentModel.getTitle());
        holder.txtLikeCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLIke.setLike(commentModel);
            }
        });
        holder.txtDissLikeCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLIke.setDissLike(commentModel);

            }
        });
    }

    @Override
    public int getItemCount() {
        return commentModelList.size();
    }


    class RvCommentViewHolder extends RecyclerView.ViewHolder {
        TextView txtSuggested, txtUnsuggested, txtDesc, txtLikeCount, txtDissLikeCount, txtUserName,txtTitle;

        public RvCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSuggested = itemView.findViewById(R.id.txt_itemComment_suggested);
            txtUnsuggested = itemView.findViewById(R.id.txt_itemComment_unSuggested);
            txtDesc = itemView.findViewById(R.id.txt_itemComment_desc);
            txtLikeCount = itemView.findViewById(R.id.txt_itemComment_likecount);
            txtDissLikeCount = itemView.findViewById(R.id.txt_itemComment_disslikecount);
            txtUserName = itemView.findViewById(R.id.txt_itemComment_userName);
            txtTitle = itemView.findViewById(R.id.txt_itemComment_title);
        }
    }

    public interface OnClick{
        void setLike(CommentModel commentModel);
        void setDissLike(CommentModel commentModel);
    }
    @SuppressLint("NotifyDataSetChanged")
    public void changeLikeViews(CommentModel commentModel, String flag){

        if (flag.equals("like")){
            int c= Integer.parseInt(commentModel.getLikecount());
            c++;
            commentModel.setLikecount(String.valueOf(c));

        }else{

            int c= Integer.parseInt(commentModel.getDislikecount());
            c++;
            commentModel.setDislikecount(String.valueOf(c));
        }
        notifyDataSetChanged();
    }
}
