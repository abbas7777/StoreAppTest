package ir.ace.storeapptest.Profile.FavorList;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
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

import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.R;

public class RvFavorAdapter extends RecyclerView.Adapter<RvFavorAdapter.RvFavorViewHolder> {

    List<Product> productList;
    Context context;
    OnDeleteClick onDeleteClick;
    OnParentClick onParentClick;

    public RvFavorAdapter(List<Product> productList, Context context,OnDeleteClick onDeleteClick,OnParentClick onParentClick) {
        this.productList = productList;
        this.context = context;
        this.onDeleteClick = onDeleteClick;
        this.onParentClick = onParentClick;
    }

    @NonNull
    @Override
    public RvFavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RvFavorViewHolder(LayoutInflater.from(context).inflate(R.layout.item_favorit,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvFavorViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.txtName.setText(p.getTitle());

        SpannableString spannableString = new SpannableString(p.getPrice());
        spannableString.setSpan(new StrikethroughSpan(), 0, p.getPprice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.txtPrice.setText(spannableString);
        holder.txtPprice.setText(p.getPprice());
        Picasso.get().load(p.getImage())
                .into(holder.imvImage);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onParentClick.onClick(p.getId());
            }
        });
        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClick.onClick(p.getId(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class RvFavorViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout parent;
        ImageView imvImage;
        TextView txtName,txtPrice,txtPprice,txtDelete;
        public RvFavorViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.rl_itemfavorit_parent);
            imvImage=itemView.findViewById(R.id.imv_itemfavorit_image);
            txtName=itemView.findViewById(R.id.txt_itemfavorit_name);
            txtPrice=itemView.findViewById(R.id.txt_itemfavorit_price);
            txtPprice=itemView.findViewById(R.id.txt_itemfavorit_pprice);
            txtDelete=itemView.findViewById(R.id.txt_itemfavorit_delete);
        }
    }

    public interface OnParentClick{
        void onClick(String id);
    }

    public interface OnDeleteClick{
        void onClick(String id,int p);
    }
}
