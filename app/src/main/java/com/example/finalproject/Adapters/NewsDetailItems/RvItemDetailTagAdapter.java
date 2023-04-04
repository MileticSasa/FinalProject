package com.example.finalproject.Adapters.NewsDetailItems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Model.Tag;
import com.example.finalproject.R;
import com.example.finalproject.databinding.RvItemTagBinding;

import java.util.ArrayList;

public class RvItemDetailTagAdapter extends RecyclerView.Adapter<RvItemDetailTagAdapter.MyHolder> {

    private ArrayList<Tag> list;

    public RvItemDetailTagAdapter(ArrayList<Tag> list) {
        this.list = list;

        for(int i = 0; i < list.size(); i++){
            if(i == 0){
                list.get(i).setChecked(true);
            }
            else {
                list.get(i).setChecked(false);
            }
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemTagBinding binding = RvItemTagBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.binding.tv.setText(list.get(position).getTitle());

        //default for first tag
        setBackgroundAndTextColor(holder, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0; i < list.size(); i++){
                    if(list.get(i).isChecked()){
                        list.get(i).setChecked(false);
                    }

                    list.get(position).setChecked(true);
                }

                setBackgroundAndTextColor(holder, position);

                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    private void setBackgroundAndTextColor(MyHolder holder, int position) {

        if(list.get(position).isChecked()){
            holder.binding.tv.setBackgroundColor(holder.itemView.getContext().getColor(R.color.dark_blue));
            holder.binding.tv.setTextColor(holder.itemView.getContext().getColor(R.color.white));
        }
        else {
            holder.binding.tv.setBackgroundColor(holder.itemView.getContext().getColor(R.color.light_grey));
            holder.binding.tv.setTextColor(holder.itemView.getContext().getColor(R.color.dark_blue));
        }
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        RvItemTagBinding binding;

        public MyHolder(@NonNull RvItemTagBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
