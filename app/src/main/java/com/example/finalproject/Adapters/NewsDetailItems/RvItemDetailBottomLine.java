package com.example.finalproject.Adapters.NewsDetailItems;

import com.example.finalproject.Adapters.NewsDetailsAdapter;
import com.example.finalproject.databinding.RvItemDetailBottomLineBinding;

public class RvItemDetailBottomLine implements DetailsAdapterItem {

    public RvItemDetailBottomLine() {
    }

    @Override
    public int getType() {
        return 7;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void bind(NewsDetailsAdapter.MyHolder holder) {
        RvItemDetailBottomLineBinding binding = (RvItemDetailBottomLineBinding) holder.binding;
    }
}
