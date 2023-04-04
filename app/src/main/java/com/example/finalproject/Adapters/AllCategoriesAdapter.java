package com.example.finalproject.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Activities.HoroscopeActivity;
import com.example.finalproject.Activities.MoneyActivity;
import com.example.finalproject.Activities.WeatherActivity;
import com.example.finalproject.CategoryNameListener;
import com.example.finalproject.Model.Category;
import com.example.finalproject.databinding.RvFunNewsItemBinding;
import com.example.finalproject.databinding.RvItemCategoriesSubcategoriesRvBinding;
import com.example.finalproject.databinding.RvItemSettingsBinding;

import java.util.ArrayList;

public class AllCategoriesAdapter extends RecyclerView.Adapter {

    private ArrayList<Category> list;

    private CategoryNameListener listener;

    public AllCategoriesAdapter(ArrayList<Category> list, CategoryNameListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 1){
            RvItemCategoriesSubcategoriesRvBinding binding = RvItemCategoriesSubcategoriesRvBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false);

            return new MyHolder1(binding);
        }
        else if(viewType == 2){
            RvFunNewsItemBinding binding = RvFunNewsItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent, false);
            return new MyHolder2(binding);
        }
        else {
            RvItemSettingsBinding binding = RvItemSettingsBinding.inflate(LayoutInflater.from(parent.getContext()),
                    parent,false);
            return new MyHolder3(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            ((MyHolder1)holder).binding.rv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
            ((MyHolder1)holder).binding.rv.setAdapter(new CategoryAndSubcategoryAdapter(list, listener));
        }
        else if(position == 1){
            ((MyHolder2)holder).binding.tvVreme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), WeatherActivity.class));
                }
            });

            ((MyHolder2)holder).binding.tvHoroskop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), HoroscopeActivity.class));
                }
            });

            ((MyHolder2)holder).binding.tvKurs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), MoneyActivity.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 1;
        }
        else if(position == 1){
            return 2;
        }
        else {
            return 3;
        }
    }

    private class MyHolder1 extends RecyclerView.ViewHolder {

        RvItemCategoriesSubcategoriesRvBinding binding;

        public MyHolder1(@NonNull RvItemCategoriesSubcategoriesRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class MyHolder2 extends RecyclerView.ViewHolder {

        RvFunNewsItemBinding binding;

        public MyHolder2(@NonNull RvFunNewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private class MyHolder3 extends RecyclerView.ViewHolder {

        RvItemSettingsBinding binding;

        public MyHolder3(@NonNull RvItemSettingsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
