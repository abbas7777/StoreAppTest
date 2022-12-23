package ir.ace.storeapptest.Search;

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

import java.util.ArrayList;
import java.util.List;

import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.R;

public class RvSearchAdapter extends RecyclerView.Adapter<RvSearchAdapter.RvSearchViewHolder> {

    Context context;
    List<Product> productList;
    OnClickParent onClickParent;

    public RvSearchAdapter(Context context, OnClickParent onClickParent) {
        this.context = context;
        this.productList = new ArrayList<>();
        this.onClickParent = onClickParent;
    }

    @NonNull
    @Override
    public RvSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RvSearchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvSearchViewHolder holder, int position) {
        if (productList.size() > 0) {
            Product p = productList.get(position);
            holder.txtName.setText(p.getTitle());

            SpannableString spannableString = new SpannableString(p.getPrice());
            spannableString.setSpan(new StrikethroughSpan(), 0, p.getPprice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.txtPrice.setText(spannableString);
            holder.txtPprice.setText(p.getPprice());
            Picasso.get().load(p.getImage())
                    .into(holder.imvImage);

            holder.rlParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickParent.onClick(p.getId());
                }
            });
        }

    }

    public void onBind(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class RvSearchViewHolder extends RecyclerView.ViewHolder {
        ImageView imvImage;
        TextView txtName, txtPrice, txtPprice;
        RelativeLayout rlParent;

        public RvSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imvImage = itemView.findViewById(R.id.imv_itemsearch_image);
            txtPprice = itemView.findViewById(R.id.txt_itemsearch_pprice);
            txtPrice = itemView.findViewById(R.id.txt_itemsearch_price);
            txtName = itemView.findViewById(R.id.txt_itemsearch_name);
            rlParent = itemView.findViewById(R.id.rl_itemsearch_parent);
        }
    }

    public interface OnClickParent {
        void onClick(String id);
    }
}
