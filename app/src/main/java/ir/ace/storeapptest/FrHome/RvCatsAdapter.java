package ir.ace.storeapptest.FrHome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.ace.storeapptest.Models.CatsItem;
import ir.ace.storeapptest.R;

public class RvCatsAdapter extends RecyclerView.Adapter<RvCatsAdapter.ViewHolderRvCatsAdapter> {


    List<CatsItem> catsItemList;
    Context context;
    OnClickCatsItem onClickCatsItem;
    public RvCatsAdapter(Context context,List<CatsItem> catsItemList,OnClickCatsItem onClickCatsItem) {
        this.context=context;
        this.catsItemList=catsItemList;
        this.onClickCatsItem=onClickCatsItem;
    }

    @Override
    public ViewHolderRvCatsAdapter onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cats, parent, false);
        return new ViewHolderRvCatsAdapter(view);
    }

    @Override
    public void onBindViewHolder( ViewHolderRvCatsAdapter holder, int position) {
        CatsItem catsItem=catsItemList.get(position);

        holder.txtname.setText(catsItem.getTitle());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickCatsItem.onClick(catsItem.getTitle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return catsItemList.size();
    }

    class ViewHolderRvCatsAdapter extends RecyclerView.ViewHolder {
        TextView txtname;
        LinearLayout linearLayout;
        public ViewHolderRvCatsAdapter( View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.txt_itemcats_name);
            linearLayout =itemView.findViewById(R.id.cv_itemcats_layout);
        }
    }

    public interface OnClickCatsItem{
        void onClick(String title);
    }
}
