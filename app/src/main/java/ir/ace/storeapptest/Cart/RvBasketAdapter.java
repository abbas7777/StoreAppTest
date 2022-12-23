package ir.ace.storeapptest.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.ace.storeapptest.Models.BasketModel;
import ir.ace.storeapptest.R;

public class RvBasketAdapter extends RecyclerView.Adapter<RvBasketAdapter.RvBasketViewHolder> {

    Context context;
    List<BasketModel> basketModelList;
    OnBasketParentClick onBasketParentClick;
    OnDeleteBasketClick onDeleteBasketClick;
    public RvBasketAdapter(Context context, List<BasketModel> basketModelList, OnBasketParentClick onBasketParentClick,OnDeleteBasketClick onDeleteBasketClick) {
        this.context = context;
        this.basketModelList = basketModelList;
        this.onBasketParentClick = onBasketParentClick;
        this.onDeleteBasketClick = onDeleteBasketClick;
    }

    @NonNull
    @Override
    public RvBasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RvBasketViewHolder(LayoutInflater.from(context).inflate(R.layout.item_basket,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvBasketViewHolder holder, int position) {
        BasketModel basketModel=basketModelList.get(position);

        holder.txtName.setText(basketModel.getTitle());
        holder.txtPrice.setText(basketModel.getPrice());
        Picasso.get().load(basketModel.getImage())
                .into(holder.imvImage);
        holder.rlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBasketParentClick.OnClick(String.valueOf(basketModel.getProductId()));
            }
        });
        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteBasketClick.OnClick(basketModel);
            }
        });


    }

    @Override
    public int getItemCount() {
        return basketModelList.size();
    }

    class RvBasketViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlParent;
        ImageView imvImage;
        TextView txtName,txtPrice;
        ImageView imvDelete;
        ElegantNumberButton elbtn;
        public RvBasketViewHolder(@NonNull View itemView) {
            super(itemView);
            imvImage=itemView.findViewById(R.id.imv_itemBasket_image);
            txtName=itemView.findViewById(R.id.txt_itembasket_name);
            txtPrice=itemView.findViewById(R.id.txt_itembasket_price);
            elbtn=itemView.findViewById(R.id.elbtn_itemBasket_number);
            rlParent=itemView.findViewById(R.id.rl_itemBasket_parent);
            imvDelete=itemView.findViewById(R.id.imv_itemBasket_delete);
        }
    }

    public interface OnBasketParentClick{
        void OnClick(String id);
    }
    public interface OnDeleteBasketClick{
        void OnClick(BasketModel basketModel);
    }

}
