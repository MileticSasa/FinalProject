package com.example.finalproject.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Model.Day;
import com.example.finalproject.databinding.RvWeatherItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyHolder> {

    private ArrayList<Day> list;

    public WeatherAdapter(ArrayList<Day> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvWeatherItemBinding binding = RvWeatherItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.binding.tvMax.setText(""+list.get(position).getTemp_max());
        holder.binding.tvMin.setText(""+list.get(position).getTemp_min());

        Picasso.get().load(list.get(position).getIcon_url()).fit().into(holder.binding.iv);

        if(position == 0)
            holder.binding.tvName.setText("Ponedeljak");
        else if(position == 1)
            holder.binding.tvName.setText("Utorak");
        else if(position == 2)
            holder.binding.tvName.setText("Sreda");
        else if(position == 3)
            holder.binding.tvName.setText("Četvrtak");
        else if(position == 4)
            holder.binding.tvName.setText("Petak");
        else if(position == 5)
            holder.binding.tvName.setText("Subota");
        else
            holder.binding.tvName.setText("Nedelja");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        RvWeatherItemBinding binding;

        public MyHolder(@NonNull RvWeatherItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
