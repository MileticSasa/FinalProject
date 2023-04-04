package com.example.finalproject.Adapters.HomeAdapterItems;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.finalproject.Adapters.HomeAdapter;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.databinding.RvItemTopBinding;

import java.util.ArrayList;

public class RvItemTop implements HomeAdapterItem{

    private ArrayList<NewsEvent> list;

    public RvItemTop(ArrayList<NewsEvent> list) {
        this.list = list;
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public void bind(HomeAdapter.MyHolder holder) {
        RvItemTopBinding binding = (RvItemTopBinding) holder.binding;

        LinearLayoutManager manager = new LinearLayoutManager(holder.itemView.getContext());
        binding.rv.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(
                        binding.rv.getContext(), manager.getOrientation());
        binding.rv.addItemDecoration(decoration);
        binding.rv.setAdapter(new RvTopAdapter(list));
    }
}
