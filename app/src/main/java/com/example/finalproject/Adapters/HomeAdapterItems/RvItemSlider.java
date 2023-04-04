package com.example.finalproject.Adapters.HomeAdapterItems;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.example.finalproject.Activities.NewsDetailActivity;
import com.example.finalproject.Adapters.HomeAdapter;
import com.example.finalproject.Adapters.SliderImageAdapter;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.R;
import com.example.finalproject.databinding.RvItemSliderBinding;

import java.util.ArrayList;

public class RvItemSlider implements HomeAdapterItem {

    private ArrayList<NewsEvent> list;
    private ArrayList<ImageView> dots;

    private int adapterPosition;

    public RvItemSlider(ArrayList<NewsEvent> list) {
        this.list = list;
        this.dots = new ArrayList<>();
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void bind(HomeAdapter.MyHolder holder) {
        RvItemSliderBinding binding = (RvItemSliderBinding) holder.binding;

        LinearLayout linearLayout = binding.ll;

        //first empty the layout
        binding.ll.removeAllViews();
        dots.clear();

        SliderImageAdapter adapter = new SliderImageAdapter(holder.itemView.getContext(), list);
        binding.vp.setAdapter(adapter);

        int dotsCount = adapter.getCount();

        for (int i = 0; i < dotsCount; i++) {
            ImageView img = new ImageView(holder.itemView.getContext());
            img.setMaxHeight(4);
            img.setMaxWidth(4);
            img.setImageResource(R.drawable.ic_nonactive_dot);
            img.setPadding(3, 2, 3, 2);

            dots.add(img);
        }

        dots.get(0).setImageResource(R.drawable.ic_active_dot);

        for (ImageView imageView : dots) {
            linearLayout.addView(imageView);
        }

        binding.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i < adapter.getCount(); i++){
                    if(i == position){
                        dots.get(i).setImageResource(R.drawable.ic_active_dot);

                        adapterPosition = position;
                    }
                    else{
                        dots.get(i).setImageResource(R.drawable.ic_nonactive_dot);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
