package com.example.finalproject.Adapters.HomeAdapterItems;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Activities.NewsDetailActivity;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.databinding.RvItemBigBinding;
import com.example.finalproject.databinding.RvItemSmallBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RvOtherAdapter extends RecyclerView.Adapter {

    private ArrayList<NewsEvent> list;
    private int limit;

    public RvOtherAdapter(ArrayList<NewsEvent> list, int limit) {
        this.list = list;
        this.limit = limit;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        }
        else
            return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == 0){
            RvItemBigBinding binding = RvItemBigBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new RvOtherAdapter.MyHolderBig(binding);
        }
        else {
            RvItemSmallBinding binding = RvItemSmallBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new RvOtherAdapter.MyHolderSmall(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            Picasso.get().load(list.get(position).getImage()).fit().into(((MyHolderBig)holder).binding.imageView);
            ((MyHolderBig)holder).binding.tvTitle.setText(list.get(position).getTitle());
            ((MyHolderBig)holder).binding.tvCategory.setText(list.get(position).getCategory().getName());

            String time = list.get(position).getCreated_at();
            ((MyHolderBig)holder).binding.tvTime.setText(time.substring(10, 16));

            ((MyHolderBig)holder).binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), NewsDetailActivity.class);
                    intent.putExtra("news", list.get(position));
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
        else {
            Picasso.get().load(list.get(position).getImage()).fit().into(((MyHolderSmall)holder).binding.iv);
            ((MyHolderSmall)holder).binding.tv1.setText(list.get(position).getTitle());
            ((MyHolderSmall)holder).binding.tv2.setText(list.get(position).getCategory().getName());

            String time = list.get(position).getCreated_at();
            ((MyHolderSmall)holder).binding.tv3.setText(time.substring(10, 16));

            ((MyHolderSmall)holder).binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), NewsDetailActivity.class);
                    intent.putExtra("news", list.get(position));
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(limit == 0) {
            return list.size();
        }
        else{
            if(list.size() > limit){
                return limit;
            }
            else {
                return list.size();
            }
        }
    }

    public class MyHolderSmall extends RecyclerView.ViewHolder {

        RvItemSmallBinding binding;

        public MyHolderSmall(@NonNull RvItemSmallBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class MyHolderBig extends RecyclerView.ViewHolder {

        RvItemBigBinding binding;

        public MyHolderBig(@NonNull RvItemBigBinding binding)  {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
