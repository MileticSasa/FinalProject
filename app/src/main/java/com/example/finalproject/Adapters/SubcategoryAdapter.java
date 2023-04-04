package com.example.finalproject.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.CategoryNameListener;
import com.example.finalproject.Model.Category;
import com.example.finalproject.databinding.RvSubcategoryItemBinding;

import java.util.ArrayList;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.MyHolder> {

    private ArrayList<Category> list;
    private CategoryNameListener listener;

    public SubcategoryAdapter(ArrayList<Category> list, CategoryNameListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvSubcategoryItemBinding binding = RvSubcategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.binding.tv.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
                    listener.onItemClick(list.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        RvSubcategoryItemBinding binding;

        public MyHolder(@NonNull RvSubcategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
