package com.example.finalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.finalproject.R;
import com.example.finalproject.Response.HoroscopeResponse;
import com.example.finalproject.Retrofit.RetrofitClient;
import com.example.finalproject.Retrofit.RetrofitService;
import com.example.finalproject.databinding.ActivityHoroscopeBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoroscopeActivity extends AppCompatActivity {

    private ActivityHoroscopeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHoroscopeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RetrofitService service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);
        service.getHoroscope().enqueue(new Callback<HoroscopeResponse>() {
            @Override
            public void onResponse(Call<HoroscopeResponse> call, Response<HoroscopeResponse> response) {

                //Moram da skratim string da bih izbegao specijalne karaktere na pocetku i na kraju teksta
                String text = response.body().data.getHoroscope().substring(6, response.body().data.getHoroscope().length() - 8);
                binding.tvContent.setText(text);

                binding.tvName.setText(response.body().data.getName());
                binding.tvDate.setText(response.body().data.getDate());
                Picasso.get().load(response.body().data.getImage_url()).fit().into(binding.iv);
            }

            @Override
            public void onFailure(Call<HoroscopeResponse> call, Throwable t) {

            }
        });
    }
}