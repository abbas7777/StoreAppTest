package ir.ace.storeapptest.FrHome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.joooonho.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import ir.ace.storeapptest.Models.Banner;
import ir.ace.storeapptest.Models.CatsItem;
import ir.ace.storeapptest.R;

public class RvImagesAdapter extends RecyclerView.Adapter<RvImagesAdapter.ViewHolderRvCatsAdapter> {


    List<Banner> bannerList;
    Context context;
    public RvImagesAdapter(Context context, List<Banner> bannerList) {
        this.context=context;
        this.bannerList=bannerList;
    }

    @Override
    public ViewHolderRvCatsAdapter onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_images, parent, false);
        return new ViewHolderRvCatsAdapter(view);
    }

    @Override
    public void onBindViewHolder( ViewHolderRvCatsAdapter holder, int position) {

        Banner banner=bannerList.get(position);
        Picasso.get().load(banner.getPic()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ViewHolderRvCatsAdapter extends RecyclerView.ViewHolder {
        SelectableRoundedImageView imageView;
        public ViewHolderRvCatsAdapter( View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.imv_itemimages_image);
        }
    }
}
