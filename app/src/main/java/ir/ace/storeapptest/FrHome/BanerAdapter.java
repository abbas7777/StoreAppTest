package ir.ace.storeapptest.FrHome;

import java.util.List;

import ir.ace.storeapptest.Models.Banner;
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class BanerAdapter extends SliderAdapter {

    List<Banner> bannerList;

    public BanerAdapter( List<Banner> bannerList) {
        this.bannerList=bannerList;
    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        imageSlideViewHolder.bindImageSlide(bannerList.get(position).getPic());

    }
}