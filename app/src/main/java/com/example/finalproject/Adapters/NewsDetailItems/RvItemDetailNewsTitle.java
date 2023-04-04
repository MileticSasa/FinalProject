package com.example.finalproject.Adapters.NewsDetailItems;

import com.example.finalproject.Adapters.NewsDetailsAdapter;
import com.example.finalproject.Model.NewsDetails;
import com.example.finalproject.databinding.RvItemDetailNewsTitleBinding;

public class RvItemDetailNewsTitle implements DetailsAdapterItem{

    private NewsDetails event;

    public RvItemDetailNewsTitle(NewsDetails event) {
        this.event = event;
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public int getPriority() {
        return 6;
    }

    @Override
    public void bind(NewsDetailsAdapter.MyHolder holder) {
        RvItemDetailNewsTitleBinding binding = (RvItemDetailNewsTitleBinding) holder.binding;

        binding.tvName.setText(event.getAuthor_name());
        binding.tvTitle.setText(event.getTitle());
        binding.tvComments.setText(""+event.getComments_count());
        binding.tvTime.setText(event.getCreated_at() + "|");
        binding.tvSourceName.setText(event.getSource());
    }
}
