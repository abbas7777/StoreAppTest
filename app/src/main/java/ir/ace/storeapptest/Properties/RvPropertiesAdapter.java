package ir.ace.storeapptest.Properties;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.ace.storeapptest.Models.PropertiesItem;
import ir.ace.storeapptest.R;

public class RvPropertiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TITLE_INT = 10001;
    private static final int VALUE_INT = 10010;
    Context context;
    List<PropertiesItem> propertiesItemLists;

    public RvPropertiesAdapter(Context context, List<PropertiesItem> propertiesItemLists) {
        this.context = context;
        this.propertiesItemLists = propertiesItemLists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TITLE_INT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_title_properties, parent, false);
            return new TitleViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_value_properties, parent, false);
            return new ValueViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PropertiesItem propertiesItem = propertiesItemLists.get(position);

        if (holder.getItemViewType()==TITLE_INT){
             ((TitleViewHolder)holder).txtTitle.setText(propertiesItem.getTitle());
        }else{
            ((ValueViewHolder)holder).txtTitleV.setText(propertiesItem.getTitle());
            ((ValueViewHolder)holder).txtValue.setText(propertiesItem.getValue());

        }
    }

    @Override
    public int getItemCount() {
        return propertiesItemLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        PropertiesItem propertiesItem = propertiesItemLists.get(position);
        if (propertiesItem.getValue().equals("")) {

            return TITLE_INT;
        } else {

            return VALUE_INT;
        }
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        public TitleViewHolder(View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txt_itemtitleproperties_title);
        }
    }

    class ValueViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleV;
        TextView txtValue;
        public ValueViewHolder(View itemView) {
            super(itemView);
            txtTitleV=itemView.findViewById(R.id.txt_itemvalueproperties_title);
            txtValue=itemView.findViewById(R.id.txt_itemvalueproperties_value);

        }
    }
}
