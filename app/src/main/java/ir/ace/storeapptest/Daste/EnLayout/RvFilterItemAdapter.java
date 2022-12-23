package ir.ace.storeapptest.Daste.EnLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

import ir.ace.storeapptest.Models.FilterModel;
import ir.ace.storeapptest.Models.ValuesItem;
import ir.ace.storeapptest.R;

public class RvFilterItemAdapter extends RecyclerView.Adapter<RvFilterItemAdapter.RvFilterItemViewHolder> {

    Context context;
    List<FilterModel> filterModelList;
    OnClickItemFilter onClickItemFilter;

    public RvFilterItemAdapter(Context context, List<FilterModel> filterModelList,OnClickItemFilter onClickItemFilter) {
        this.context = context;
        this.filterModelList = filterModelList;
        this.onClickItemFilter = onClickItemFilter;
    }

    @NonNull
    @Override
    public RvFilterItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RvFilterItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_filter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvFilterItemViewHolder holder, int position) {
        FilterModel filterModel = filterModelList.get(position);
        int p=position;
        holder.txtTitle.setText(filterModel.getTitle());
        List<ValuesItem> valuesItemList=filterModel.getValues();
        for (int i = 0; i <valuesItemList.size() ; i++) {
            if(valuesItemList.get(i).isCheck()){
                if (holder.txtFilters.getText().toString().equals("")){
                    holder.txtFilters.setText(valuesItemList.get(i).getTitle());

                }else{
                    holder.txtFilters.setText(holder.txtFilters.getText().toString()+"،"+valuesItemList.get(i).getTitle());

                }
            }
        }
        holder.rlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemFilter.onClick(filterModel.getValues(),p);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filterModelList.size();
    }

    class RvFilterItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtFilters;
        RelativeLayout rlParent;
        public RvFilterItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_itemFilter_title);
            txtFilters = itemView.findViewById(R.id.txt_itemFilter_count);
            rlParent = itemView.findViewById(R.id.rl_itemFilter_parent);
        }
    }

    public interface OnClickItemFilter{
        void onClick(List<ValuesItem> valuesItemList,int p);
    }
}
