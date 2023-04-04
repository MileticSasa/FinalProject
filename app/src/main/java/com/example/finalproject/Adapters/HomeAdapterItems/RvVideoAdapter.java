package com.example.finalproject.Adapters.HomeAdapterItems;

import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.databinding.RvItemVideoItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RvVideoAdapter extends RecyclerView.Adapter<RvVideoAdapter.MyHolder> {

    private ArrayList<NewsEvent> list;
    private int limit;

    public RvVideoAdapter(ArrayList<NewsEvent> list, int limit) {
        this.list = list;
        this.limit = limit;
    }

    @NonNull
    @Override
    public RvVideoAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemVideoItemBinding binding = RvItemVideoItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RvVideoAdapter.MyHolder holder, int position) {

        holder.binding.videoView.setVideoURI(Uri.parse
                ("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        //holder.binding.videoView.start();

        Picasso.get().load(list.get(position).getImage()).fit().into(holder.binding.iv);
        holder.binding.tvTitle.setText(list.get(position).getTitle());
        holder.binding.tvCategory.setText(list.get(position).getCategory().getName());
        int color = Color.parseColor(list.get(position).getCategory().getColor());
        holder.binding.tvCategory.setTextColor(color);

        holder.binding.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.childLayout.setVisibility(View.GONE);
                holder.binding.childLayout1.setVisibility(View.VISIBLE);
                MediaController controller = new MediaController(holder.itemView.getContext());
                holder.binding.videoView.setMediaController(controller);
                controller.setAnchorView(holder.binding.videoView);
                holder.binding.videoView.start();
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

    public class MyHolder extends RecyclerView.ViewHolder {

        private RvItemVideoItemBinding binding;

        public MyHolder(@NonNull RvItemVideoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
