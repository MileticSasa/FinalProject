package com.example.finalproject.Adapters.NewsDetailItems;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.finalproject.Adapters.NewsDetailsAdapter;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.databinding.RvItemDetailRelatedNewsRvBinding;

import java.util.ArrayList;

public class RvItemDetailRelatedNews implements DetailsAdapterItem {

    private ArrayList<NewsEvent> list;

    public RvItemDetailRelatedNews(ArrayList<NewsEvent> list) {
        this.list = list;
    }

    @Override
    public int getType() {
        return 6;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void bind(NewsDetailsAdapter.MyHolder holder) {
        RvItemDetailRelatedNewsRvBinding binding = (RvItemDetailRelatedNewsRvBinding) holder.binding;

        binding.rv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        binding.rv.setAdapter(new RvItemDetailRelatedNewsAdapter(list, 5));
    }
}
