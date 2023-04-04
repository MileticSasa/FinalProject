package com.example.finalproject.Adapters.HomeAdapterItems;

import android.graphics.Color;

import com.example.finalproject.Adapters.HomeAdapter;
import com.example.finalproject.R;
import com.example.finalproject.databinding.RvItemTitleBinding;

public class RvItemTitle implements HomeAdapterItem{

    private int priority;
    private String title, color;

    public RvItemTitle(String title, String color) {
        this.title = title;
        this.color = color;
    }

    @Override
    public int getType() {
        return 6;
    }

    @Override
    public int getPriority() {
        return priority;
    }


    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public void bind(HomeAdapter.MyHolder holder) {
        RvItemTitleBinding binding = (RvItemTitleBinding) holder.binding;
        binding.tv.setText(title);
        binding.underLine.setBackgroundColor(Color.parseColor(color));

        if(title.equals("Video")) {
            binding.parent.setBackgroundColor(holder.itemView.getContext().getColor(R.color.background_blue));
            binding.tv.setTextColor(holder.itemView.getContext().getColor(R.color.white));
        }
        else {
            binding.parent.setBackgroundColor(holder.itemView.getContext().getColor(R.color.transparent));
            binding.tv.setTextColor(holder.itemView.getContext().getColor(R.color.black));
        }
    }
}
