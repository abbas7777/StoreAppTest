package ir.ace.storeapptest.Detail;

import java.util.List;

import ir.ace.storeapptest.Models.Banner;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class SliderAdapter extends ss.com.bannerslider.adapters.SliderAdapter {

    List<String> stringList;

    public SliderAdapter(List<String> stringList) {
        this.stringList=stringList;
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        imageSlideViewHolder.bindImageSlide(stringList.get(position));

    }
}