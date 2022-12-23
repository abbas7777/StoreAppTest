package ir.ace.storeapptest.Daste;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.ace.storeapptest.Models.CatsItem;
import ir.ace.storeapptest.R;

public class RvDasteAdapter extends RecyclerView.Adapter<RvDasteAdapter.RvDasteViewHolder> {

    Context context;
    List<CatsItem> catsItemList;
    OnCLickDasteItem onCLickDasteItem;

    public RvDasteAdapter(Context context, List<CatsItem> catsItemList,OnCLickDasteItem onCLickDasteItem) {
        this.context = context;
        this.catsItemList = catsItemList;
        this.onCLickDasteItem = onCLickDasteItem;
    }


    @Override
    public RvDasteViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_datste,parent,false);
        return new RvDasteViewHolder(view);
    }

    @Override
    public void onBindViewHolder( RvDasteViewHolder holder, int position) {
        CatsItem catsItem=catsItemList.get(position);
        Picasso.get().load(catsItem.getPicurl()).into(holder.imvImage);
        holder.txtName.setText(catsItem.getTitle());
        holder.rlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCLickDasteItem.onClick(catsItem.getTitle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return catsItemList.size();
    }

    public class RvDasteViewHolder extends RecyclerView.ViewHolder {
        ImageView imvImage;
        TextView txtName;
        RelativeLayout rlParent;
        public RvDasteViewHolder( View itemView) {
            super(itemView);
            imvImage=itemView.findViewById(R.id.imv_itemdaste_image);
            txtName=itemView.findViewById(R.id.txt_itemdaste_name);
            rlParent=itemView.findViewById(R.id.rl_itemdaste_parent);
        }
    }

    public interface OnCLickDasteItem{

        void onClick(String title);
    }
}
