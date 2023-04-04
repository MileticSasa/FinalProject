package com.example.finalproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.finalproject.Adapters.HomeAdapterItems.HomeAdapterItem;
import com.example.finalproject.Adapters.HomeAdapterItems.RvItemOther;
import com.example.finalproject.Adapters.HomeAdapterItems.RvItemSlider;
import com.example.finalproject.Adapters.HomeAdapterItems.RvItemTabview;
import com.example.finalproject.Adapters.HomeAdapterItems.RvItemTitle;
import com.example.finalproject.Adapters.HomeAdapterItems.RvItemTop;
import com.example.finalproject.Adapters.HomeAdapterItems.RvItemVideo;
import com.example.finalproject.Model.CategoryNews;
import com.example.finalproject.Model.EventsLists;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.databinding.RvItemOtherBinding;
import com.example.finalproject.databinding.RvItemSliderBinding;
import com.example.finalproject.databinding.RvItemTabViewBinding;
import com.example.finalproject.databinding.RvItemTitleBinding;
import com.example.finalproject.databinding.RvItemTopBinding;
import com.example.finalproject.databinding.RvItemVideoBinding;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyHolder> {

    private Context context;
    private EventsLists lists;

    private ArrayList<HomeAdapterItem> items;

    public HomeAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public HomeAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewBinding binding = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case 1:
                binding = RvItemSliderBinding.inflate(inflater, parent, false);
                break;
            case 2:
                binding = RvItemTopBinding.inflate(inflater, parent, false);
                break;
            case 3:
                binding = RvItemTabViewBinding.inflate(inflater, parent, false);
                break;
            case 4:
                binding = RvItemOtherBinding.inflate(inflater, parent, false);
                break;
            case 5:
                binding = RvItemVideoBinding.inflate(inflater, parent, false);
                break;
            default:
                binding = RvItemTitleBinding.inflate(inflater, parent, false);
        }

        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.MyHolder holder, int position) {
        if(items != null)
            items.get(position).bind(holder);
    }

    @Override
    public int getItemCount() {
        if(items == null)
            return 0;
        else
            return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }


    public void addEvents(EventsLists eventsLists, ArrayList<NewsEvent> filteredList){

        ArrayList<CategoryNews> es = null;

        if(eventsLists == null){
            setFilteredList(filteredList);
        }
        else {

            es = eventsLists.getCategory();
            items.clear();

            if (eventsLists.getSlider() != null) {
                items.add(new RvItemSlider(eventsLists.getSlider()));
            }

            if (eventsLists.getTop() != null) {
                items.add(new RvItemTop(eventsLists.getTop()));
            }

            if (eventsLists.getMost_read() != null || eventsLists.getLatest() != null ||
                    eventsLists.getMost_comented() != null) {
                items.add(new RvItemTabview(eventsLists.getLatest(), eventsLists.getMost_read(), eventsLists.getMost_comented(), 5));
                //items.add(new NovoItem(eventsLists.getLatest(), eventsLists.getMost_read(), eventsLists.getMost_comented()));
            }

            if(eventsLists.getEditors_choice() != null && eventsLists.getEditors_choice().size() > 0){
                items.add(new RvItemTitle("Izbor urednika", eventsLists.getEditors_choice().get(0).getCategory().getColor()));
                items.add(new RvItemSlider(eventsLists.getEditors_choice()));
            }

            if(eventsLists.getVideos() != null){
                items.add(new RvItemTitle("Video", eventsLists.getVideos().get(0).getCategory().getColor()));
                items.add(new RvItemVideo(eventsLists.getVideos(), 3));
            }


            for(CategoryNews e : es){
                if(e.getNews() != null && e.getNews().size() > 0){
                    items.add(new RvItemTitle(e.getTitle(), e.getColor()));
                    items.add(new RvItemOther(e.getNews(), 5)); //limit recView to 5 items
                }
            }

        }

        notifyDataSetChanged();
    }

    public void setFilteredList(ArrayList<NewsEvent> list){

        items.clear();
        items.add(new RvItemOther(list, 0)); //0 for limit
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        public ViewBinding binding;

        public MyHolder(@NonNull ViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
