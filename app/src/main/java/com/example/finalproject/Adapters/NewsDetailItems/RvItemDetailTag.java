package com.example.finalproject.Adapters.NewsDetailItems;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.finalproject.Adapters.NewsDetailsAdapter;
import com.example.finalproject.Model.Tag;
import com.example.finalproject.databinding.RvItemDetailTagRvBinding;

import java.util.ArrayList;

public class RvItemDetailTag implements DetailsAdapterItem{

    private ArrayList<Tag> list;

    public RvItemDetailTag(ArrayList<Tag> list) {
        this.list = list;
    }

    @Override
    public int getType() {
        return 4;
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void bind(NewsDetailsAdapter.MyHolder holder) {
        RvItemDetailTagRvBinding binding = (RvItemDetailTagRvBinding) holder.binding;

        binding.rv.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 3));
        binding.rv.setAdapter(new RvItemDetailTagAdapter(list));
    }
}
