package com.example.finalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.finalproject.Adapters.WeatherAdapter;
import com.example.finalproject.Model.Day;
import com.example.finalproject.Model.Weather;
import com.example.finalproject.R;
import com.example.finalproject.Response.WeatherResponse;
import com.example.finalproject.Retrofit.RetrofitClient;
import com.example.finalproject.Retrofit.RetrofitService;
import com.example.finalproject.databinding.ActivityWeatherBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    ActivityWeatherBinding binding;

    ArrayList<Day> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RetrofitService service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);
        service.getWeather().enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Weather weather = response.body().data;

                list.add(weather.getDay_0());
                list.add(weather.getDay_1());
                list.add(weather.getDay_2());
                list.add(weather.getDay_3());
                list.add(weather.getDay_4());
                list.add(weather.getDay_5());
                list.add(weather.getDay_6());

                binding.tvName.setText(weather.getName());
                binding.tvTemp.setText(weather.getTemp());
                Picasso.get().load(weather.getIcon_url()).fit().into(binding.iv);
                binding.tvDescr.setText(weather.getDescription());
                binding.tvHumidity.setText(weather.getHumidity());
                binding.tvWind.setText(weather.getWind());

                binding.rv.setLayoutManager(new LinearLayoutManager(WeatherActivity.this));
                binding.rv.setAdapter(new WeatherAdapter(list));
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
            }
        });
    }
}