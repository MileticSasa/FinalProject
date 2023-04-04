package com.example.finalproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.Activities.NewsDetailActivity;
import com.example.finalproject.databinding.RvItemSmallBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyHolder> {

    private ArrayList<NewsEvent> list;
    private Context context;


    public EventAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemSmallBinding binding = RvItemSmallBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.binding.tv1.setText(list.get(position).getTitle());
        holder.binding.tv2.setText(list.get(position).getCategory().getName());
        holder.binding.tv3.setText(" | " + list.get(position).getCreated_at());
        Picasso.get().load(list.get(position).getImage()).into(holder.binding.iv);

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
        if(list == null){
            return 0;
        }

        return list.size();
    }

    public void setList(ArrayList<NewsEvent> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private RvItemSmallBinding binding;

        public MyHolder(RvItemSmallBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
