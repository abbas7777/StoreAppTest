package ir.ace.storeapptest.Daste.EnLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.ace.storeapptest.Models.ValuesItem;
import ir.ace.storeapptest.R;

public class RvFilterValuesAdapter extends RecyclerView.Adapter<RvFilterValuesAdapter.RvFilterValuesViewHolder> {

    Context context;
    List<ValuesItem> valuesItemList;
    OnFilterValueItemClick onFilterValueItemClick;

    public RvFilterValuesAdapter(Context context, List<ValuesItem> valuesItemList, OnFilterValueItemClick onFilterValueItemClick) {
        this.context = context;
        this.valuesItemList = valuesItemList;
        this.onFilterValueItemClick = onFilterValueItemClick;
    }

    @NonNull
    @Override
    public RvFilterValuesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RvFilterValuesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_value, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvFilterValuesViewHolder holder, int position) {
        ValuesItem item = valuesItemList.get(position);
        int p = position;
        holder.checkBox.setText(item.getTitle());
        holder.checkBox.setChecked(item.isCheck());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    onFilterValueItemClick.onClick(item.getTitle(), p);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return valuesItemList.size();
    }

    class RvFilterValuesViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public RvFilterValuesViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.chb_itemFilterValues);
        }
    }

    public interface OnFilterValueItemClick {
        void onClick(String title, int p);
    }
}
