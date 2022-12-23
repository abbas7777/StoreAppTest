package ir.ace.storeapptest.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.ace.storeapptest.Models.ButtonProfileModel;
import ir.ace.storeapptest.R;

public class RvBtnProfileAdapter extends RecyclerView.Adapter<RvBtnProfileAdapter.RvBtnProfileViewHolder> {

    Context context;
    List<ButtonProfileModel> buttonProfileModelList;
    OnBtnProfileClick onBtnProfileClick;

    public RvBtnProfileAdapter(Context context, List<ButtonProfileModel> buttonProfileModelList,OnBtnProfileClick onBtnProfileClick) {
        this.context = context;
        this.buttonProfileModelList = buttonProfileModelList;
        this.onBtnProfileClick = onBtnProfileClick;
    }

    @NonNull
    @Override
    public RvBtnProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RvBtnProfileViewHolder(LayoutInflater.from(context).inflate(R.layout.item_btnprofile,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvBtnProfileViewHolder holder, int position) {
        ButtonProfileModel buttonProfileModel=buttonProfileModelList.get(position);
        holder.txttitle.setText(buttonProfileModel.getTitle());
        holder.imvIcon.setImageDrawable(ContextCompat.getDrawable(context,buttonProfileModel.getIdIcon()));
        holder.rlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBtnProfileClick.onClick(buttonProfileModel.getIdIcon());
            }
        });

    }

    @Override
    public int getItemCount() {
        return buttonProfileModelList.size();
    }

    class RvBtnProfileViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlParent;
        ImageView imvIcon;
        TextView txttitle;
        public RvBtnProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            rlParent=itemView.findViewById(R.id.rl_itemBtnProfile_parent);
            imvIcon=itemView.findViewById(R.id.imv_itemBtnProfile_iamge);
            txttitle=itemView.findViewById(R.id.txt_itemBtnProfile_title);
        }
    }

    public interface OnBtnProfileClick{
        void onClick(int i);
    }
}
