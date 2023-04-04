package com.example.finalproject.Adapters.HomeAdapterItems;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Activities.NewsDetailActivity;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.databinding.RvTabViewRvItemBinding;

import java.util.ArrayList;

public class TabViewRvAdapter extends RecyclerView.Adapter<TabViewRvAdapter.MyHolder> {

    private ArrayList<NewsEvent> list;
    int limit;

    public TabViewRvAdapter(ArrayList<NewsEvent> list, int limit) {
        this.list = list;
        this.limit = limit;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvTabViewRvItemBinding binding = RvTabViewRvItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        if(list != null){
            holder.binding.tv.setText(list.get(position).getTitle());

            String time = list.get(position).getCreated_at();
            holder.binding.tvTime.setText(time.substring(11, 16));
        }

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

    public void setList(ArrayList<NewsEvent> list){
        this.list = list;
        notifyDataSetChanged();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        private RvTabViewRvItemBinding binding;

        public MyHolder(@NonNull RvTabViewRvItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
