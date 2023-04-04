package com.example.finalproject.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.CategoryNameListener;
import com.example.finalproject.Model.Category;
import com.example.finalproject.R;
import com.example.finalproject.databinding.RvCategorySubcategoryItemBinding;

import java.util.ArrayList;

public class CategoryAndSubcategoryAdapter extends RecyclerView.Adapter {

    private ArrayList<Category> list;
    private CategoryNameListener listener;

    public CategoryAndSubcategoryAdapter(ArrayList<Category> list, CategoryNameListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvCategorySubcategoryItemBinding binding = RvCategorySubcategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new CategorySubcategoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((CategorySubcategoryHolder)holder).binding.tv.setText(list.get(position).getName());
        ((CategorySubcategoryHolder)holder).binding.view.setBackgroundColor(Color.parseColor(list.get(position).getColor()));

        ((CategorySubcategoryHolder)holder).binding.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onItemClick(list.get(position).getName());
                }
            }
        });

        if(list.get(position).getSubcategories().size() > 0)
        {
            ((CategorySubcategoryHolder)holder).binding.btn.setVisibility(View.VISIBLE);
            ((CategorySubcategoryHolder)holder).binding.btn.setImageResource(R.drawable.ic_arrow_down);

            handleBtnClick(holder, position);
        }
        else {
            ((CategorySubcategoryHolder)holder).binding.btn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void handleBtnClick(RecyclerView.ViewHolder holder, int position) {

        //set everything to false
        for(Category category : list){
            category.isChecked = false;
        }

        ((CategorySubcategoryHolder)holder).binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!list.get(position).isChecked){
                    ((CategorySubcategoryHolder)holder).binding.rv.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(((CategorySubcategoryHolder)holder).itemView.getContext(),
                            R.anim.anim_rotate_up);
                    animation.setDuration(400);
                    ((CategorySubcategoryHolder)holder).binding.btn.startAnimation(animation);
                    ((CategorySubcategoryHolder)holder).binding.btn.setImageResource(R.drawable.ic_arrow_up);

                    ((CategorySubcategoryHolder)holder).binding.rv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
                    ((CategorySubcategoryHolder)holder).binding.rv.setAdapter(
                            new SubcategoryAdapter(list.get(position).getSubcategories(), listener));

                    list.get(position).isChecked = true;
                }
                else {
                    ((CategorySubcategoryHolder)holder).binding.btn.setImageResource(R.drawable.ic_arrow_up);
                    Animation animation = AnimationUtils.loadAnimation(((CategorySubcategoryHolder)holder).itemView.getContext(),
                            R.anim.anim_rotate_down);
                    animation.setDuration(400);
                    ((CategorySubcategoryHolder)holder).binding.btn.startAnimation(animation);
                    ((CategorySubcategoryHolder)holder).binding.btn.setImageResource(R.drawable.ic_arrow_down);
                    ((CategorySubcategoryHolder)holder).binding.rv.setVisibility(View.GONE);

                    list.get(position).isChecked = false;
                }
            }
        });
    }


    public class CategorySubcategoryHolder extends RecyclerView.ViewHolder {

        RvCategorySubcategoryItemBinding binding;

        public CategorySubcategoryHolder(@NonNull RvCategorySubcategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
