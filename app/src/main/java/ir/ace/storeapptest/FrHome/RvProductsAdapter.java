package ir.ace.storeapptest.FrHome;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.ace.storeapptest.Models.Product;
import ir.ace.storeapptest.R;

public class RvProductsAdapter extends RecyclerView.Adapter<RvProductsAdapter.RvProductsViewHolder> {

   Context context;
   List<Product> productList;
   OnClick onClick;
    public RvProductsAdapter(Context context,List<Product> productList,OnClick onClick) {
        this.context=context;
        this.productList=productList;
        this.onClick=onClick;
    }

    @Override
    public RvProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        if (viewType==-1){
            view= LayoutInflater.from(context).inflate(R.layout.item_txtsh,parent,false);

        }else{
            view= LayoutInflater.from(context).inflate(R.layout.item_products,parent,false);

        }
        return new  RvProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder( RvProductsViewHolder holder, int position) {

        if (position!=0) {
            Product p = productList.get(position);

            SpannableString spannableString = new SpannableString(p.getPrice());
            spannableString.setSpan(new StrikethroughSpan(), 0, p.getPprice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            Picasso.get().load(p.getImage()).into(holder.imvimage);
            holder.txtname.setText(p.getTitle());
            holder.txtprice.setText(p.getPprice());
            holder.txtpprice.setText(spannableString);
            holder.cvparent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onClik(p.getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position==0){
            return -1;
        }else {
            return super.getItemViewType(position);
        }
    }

    class RvProductsViewHolder extends RecyclerView.ViewHolder{
        ImageView imvimage;
        CardView cvparent;
        TextView txtname,txtprice,txtpprice;
        public RvProductsViewHolder( View itemView) {
            super(itemView);
            imvimage=itemView.findViewById(R.id.imv_itemproduct_image);
            txtname=itemView.findViewById(R.id.txt_itemproduct_name);
            txtpprice=itemView.findViewById(R.id.txt_itemproduct_pprice);
            txtprice=itemView.findViewById(R.id.txt_itemproduct_price);
            cvparent=itemView.findViewById(R.id.cv_itemproduct_parent);
        }
    }
    public interface OnClick{
        void onClik(String id);
    }
}
