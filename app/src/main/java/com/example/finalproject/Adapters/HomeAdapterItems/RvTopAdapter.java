package com.example.finalproject.Adapters.HomeAdapterItems;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Activities.NewsDetailActivity;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.databinding.RvItemSmallBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RvTopAdapter extends RecyclerView.Adapter<RvTopAdapter.MyHolder> {

    private ArrayList<NewsEvent> list;

    public RvTopAdapter(ArrayList<NewsEvent> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemSmallBinding binding = RvItemSmallBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Picasso.get().load(list.get(position).getImage()).fit().into(holder.binding.iv);
        holder.binding.tv1.setText(list.get(position).getTitle());
        holder.binding.tv2.setText(list.get(position).getCategory().getName());
        holder.binding.tv2.setTextColor(Color.parseColor(list.get(position).getCategory().getColor()));

        String time = list.get(position).getCreated_at();
        holder.binding.tv3.setText(time.substring(10, 16));

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), NewsDetailActivity.class);
                intent.putExtra("news", list.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        RvItemSmallBinding binding;

        public MyHolder(@NonNull RvItemSmallBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
