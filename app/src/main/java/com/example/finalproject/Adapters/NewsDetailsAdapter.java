package com.example.finalproject.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.finalproject.Adapters.NewsDetailItems.DetailsAdapterItem;
import com.example.finalproject.Adapters.NewsDetailItems.RvItemDetailBottomLine;
import com.example.finalproject.Adapters.NewsDetailItems.RvItemDetailComment;
import com.example.finalproject.Adapters.NewsDetailItems.RvItemDetailRelatedNews;
import com.example.finalproject.Adapters.NewsDetailItems.RvItemDetailShare;
import com.example.finalproject.Adapters.NewsDetailItems.RvItemDetailTag;
import com.example.finalproject.Adapters.NewsDetailItems.RvItemDetailNewsTitle;
import com.example.finalproject.Adapters.NewsDetailItems.RvItemDetailTitle;
import com.example.finalproject.Adapters.NewsDetailItems.RvItemDetailWebView;
import com.example.finalproject.Model.Comment;
import com.example.finalproject.Model.NewsDetails;
import com.example.finalproject.databinding.RvItemDetailBottomLineBinding;
import com.example.finalproject.databinding.RvItemDetailCommentBinding;
import com.example.finalproject.databinding.RvItemDetailNewsTitleBinding;
import com.example.finalproject.databinding.RvItemDetailRelatedNewsRvBinding;
import com.example.finalproject.databinding.RvItemDetailShareBinding;
import com.example.finalproject.databinding.RvItemDetailTagRvBinding;
import com.example.finalproject.databinding.RvItemDetailTitleBinding;
import com.example.finalproject.databinding.RvItemDetailWebViewBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NewsDetailsAdapter extends RecyclerView.Adapter<NewsDetailsAdapter.MyHolder> {


    private ArrayList<DetailsAdapterItem> items;

    public NewsDetailsAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewBinding binding = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case 1:
                binding = RvItemDetailNewsTitleBinding.inflate(inflater, parent, false);
                break;
            case 2:
                binding = RvItemDetailWebViewBinding.inflate(inflater, parent, false);
                break;
            case 3:
                binding = RvItemDetailShareBinding.inflate(inflater, parent, false);
                break;
            case 4:
                binding = RvItemDetailTagRvBinding.inflate(inflater,parent, false);
                break;
            case 5:
                binding = RvItemDetailCommentBinding.inflate(inflater, parent, false);
                break;
            case 6:
                binding = RvItemDetailRelatedNewsRvBinding.inflate(inflater, parent, false);
                break;
            case 7:
                binding = RvItemDetailBottomLineBinding.inflate(inflater, parent, false);
                break;
            default:
                binding = RvItemDetailTitleBinding.inflate(inflater, parent, false);
                break;
        }

        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        if(items != null){
            items.get(position).bind(holder);
        }
    }

    @Override
    public int getItemCount() {
        if(items != null)
            return items.size();
        else
            return 0;
    }

    public void setLists(NewsDetails details){

        if(details != null){
            this.items.add(new RvItemDetailNewsTitle(details));
            this.items.add(new RvItemDetailWebView(details.getUrl()));

            this.items.add(new RvItemDetailTitle("Podeli", 4)); //4 is priority of share
            this.items.add(new RvItemDetailShare(details.getUrl()));

            if(details.getTags() != null) {
                //setting priority for detailTitle before adding it to list
                RvItemDetailTitle detailTitle = new RvItemDetailTitle("Tagovi", 3); //3 is priority of tags
                this.items.add(detailTitle);
                this.items.add(new RvItemDetailTag(details.getTags()));
            }

            if(details.getRelated_news() != null) {
                RvItemDetailTitle detailTitle1 = new RvItemDetailTitle("Povezane vesti", 1); //1 is priority of relatedNews
                this.items.add(detailTitle1);
                this.items.add(new RvItemDetailRelatedNews(details.getRelated_news()));
            }
        }

        this.items.add(new RvItemDetailBottomLine());

        Collections.sort(items, new Comparator<DetailsAdapterItem>() {
            @Override
            public int compare(DetailsAdapterItem item1, DetailsAdapterItem item2) {
                return Integer.valueOf(item2.getPriority()).compareTo(item1.getPriority());
            }
        });

        notifyDataSetChanged();
    }

    public void setComments(ArrayList<Comment> comments, int commentCount, int newsId){
        RvItemDetailTitle detailTitle = new RvItemDetailTitle("Komentari", 2); //2 is priority of comments
        this.items.add(detailTitle);
        this.items.add(new RvItemDetailComment(comments, commentCount, newsId));

        Collections.sort(items, new Comparator<DetailsAdapterItem>() {
            @Override
            public int compare(DetailsAdapterItem item1, DetailsAdapterItem item2) {
                return Integer.valueOf(item2.getPriority()).compareTo(item1.getPriority());
            }
        });

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        public ViewBinding binding;

        public MyHolder(@NonNull ViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
