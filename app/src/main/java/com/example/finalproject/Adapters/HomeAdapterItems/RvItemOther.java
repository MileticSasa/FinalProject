package com.example.finalproject.Adapters.HomeAdapterItems;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.finalproject.Adapters.HomeAdapter;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.databinding.RvItemOtherBinding;

import java.util.ArrayList;

public class RvItemOther implements HomeAdapterItem{

    private ArrayList<NewsEvent> list;
    private int limit;

    public RvItemOther(ArrayList<NewsEvent> list, int limit) {
        this.list = list;
        this.limit = limit;
    }

    @Override
    public int getType() {
        return 4;
    }

    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public void bind(HomeAdapter.MyHolder holder) {
        RvItemOtherBinding binding = (RvItemOtherBinding) holder.binding;

        LinearLayoutManager manager = new LinearLayoutManager(holder.itemView.getContext());
        binding.rv.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(
                binding.rv.getContext(), manager.getOrientation());
        binding.rv.addItemDecoration(decoration);
        binding.rv.setAdapter(new RvOtherAdapter(list, limit));
    }
}
