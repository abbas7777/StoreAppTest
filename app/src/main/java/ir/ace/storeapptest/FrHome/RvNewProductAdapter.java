package ir.ace.storeapptest.FrHome;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.joooonho.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import ir.ace.storeapptest.Models.Banner;
import ir.ace.storeapptest.Models.NewProduct;
import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.R;

public class RvNewProductAdapter extends RecyclerView.Adapter<RvNewProductAdapter.ViewHolderRvCatsAdapter> {


    List<NewProduct> productList;
    Context context;
    OnNewProductItemClick onNewProductItemClick;
    public RvNewProductAdapter(Context context, List<NewProduct> productList,OnNewProductItemClick onNewProductItemClick) {
        this.context=context;
        this.productList=productList;
        this.onNewProductItemClick=onNewProductItemClick;
    }

    @Override
    public ViewHolderRvCatsAdapter onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newproducts, parent, false);
        return new ViewHolderRvCatsAdapter(view);
    }

    @Override
    public void onBindViewHolder( ViewHolderRvCatsAdapter holder, int position) {

        NewProduct p=productList.get(position);
        SpannableString spannableString = new SpannableString(p.getPrice());
        spannableString.setSpan(new StrikethroughSpan(), 0, p.getPrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Picasso.get().load(p.getPic()).into(holder.imageView);
        holder.txtname.setText(p.getTitle());
        holder.txtprice.setText(p.getPprice());
        holder.txtpprice.setText(spannableString);
        holder.linParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewProductItemClick.onClick(p.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolderRvCatsAdapter extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtname,txtprice,txtpprice;
        LinearLayout linParent;
        public ViewHolderRvCatsAdapter( View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.imv_itemnewproduct_image);
            txtname=itemView.findViewById(R.id.txt_itemnewproduct_name);
            txtpprice=itemView.findViewById(R.id.txt_itemnewproduct_pprice);
            txtprice=itemView.findViewById(R.id.txt_itemnewproduct_price);
            linParent=itemView.findViewById(R.id.linlay_itemNewProduct_parent);
        }
    }

    public interface OnNewProductItemClick{
        void onClick(String id);
    }
}
