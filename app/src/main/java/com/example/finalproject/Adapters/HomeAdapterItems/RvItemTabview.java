package com.example.finalproject.Adapters.HomeAdapterItems;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.finalproject.Adapters.HomeAdapter;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.R;
import com.example.finalproject.databinding.RvItemTabViewBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class RvItemTabview implements HomeAdapterItem {

    private ArrayList<NewsEvent> latest, most_read, most_commented;
    private int limit;

    public RvItemTabview(ArrayList<NewsEvent> latest, ArrayList<NewsEvent> most_read,
                         ArrayList<NewsEvent> most_commented, int limit) {
        this.latest = latest;
        this.most_read = most_read;
        this.most_commented = most_commented;
        this.limit = limit;
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void bind(HomeAdapter.MyHolder holder) {
        RvItemTabViewBinding binding = (RvItemTabViewBinding) holder.binding;

        LinearLayoutManager manager = new LinearLayoutManager(holder.itemView.getContext());
        binding.rv.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(
                binding.rv.getContext(), manager.getOrientation());
        binding.rv.addItemDecoration(decoration);

        //default settings
        TabViewRvAdapter adapter = new TabViewRvAdapter(latest, 5);
        binding.rv.setAdapter(adapter);

        binding.tabLaout.removeAllTabs();
        binding.tabLaout.addTab(binding.tabLaout.newTab().setText("Najnovije"));
        binding.tabLaout.addTab(binding.tabLaout.newTab().setText("Najčitanije"));
        binding.tabLaout.addTab(binding.tabLaout.newTab().setText("Komentari"));

        binding.tabLaout.setSelectedTabIndicatorColor(Color.parseColor(latest.get(0).getCategory().getColor()));

        binding.tabLaout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    adapter.setList(latest);
                    binding.tabLaout.setSelectedTabIndicatorColor(Color.parseColor(latest.get(0).getCategory().getColor()));
                }
                else if(tab.getPosition() == 1) {
                    adapter.setList(most_read);
                    binding.tabLaout.setSelectedTabIndicatorColor(Color.parseColor(most_read.get(0).getCategory().getColor()));
                }
                else {
                    adapter.setList(most_commented);
                    binding.tabLaout.setSelectedTabIndicatorColor(Color.parseColor(most_commented.get(0).getCategory().getColor()));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
