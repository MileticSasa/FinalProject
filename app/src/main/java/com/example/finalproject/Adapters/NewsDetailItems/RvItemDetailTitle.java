package com.example.finalproject.Adapters.NewsDetailItems;

import com.example.finalproject.Adapters.NewsDetailsAdapter;
import com.example.finalproject.databinding.RvItemDetailNewsTitleBinding;
import com.example.finalproject.databinding.RvItemDetailTitleBinding;
import com.example.finalproject.databinding.RvItemTitleBinding;

public class RvItemDetailTitle implements DetailsAdapterItem{

    private int priority;
    private String title;

    public RvItemDetailTitle(String title, int priority) {
        this.title = title;
        this.priority = priority;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getPriority() {
        return priority;
    }


    @Override
    public void bind(NewsDetailsAdapter.MyHolder holder) {
        RvItemDetailTitleBinding binding = (RvItemDetailTitleBinding) holder.binding;
        binding.tv.setText(title);
    }
}
