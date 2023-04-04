package com.example.finalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.finalproject.Adapters.NewsDetailItems.RvCommentAdapter;
import com.example.finalproject.Adapters.NewsDetailsAdapter;
import com.example.finalproject.Model.Comment;
import com.example.finalproject.Model.NewsDetails;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.Model.Tag;
import com.example.finalproject.Response.CommentsResponse;
import com.example.finalproject.Response.NewsDetailsResponse;
import com.example.finalproject.Retrofit.RetrofitClient;
import com.example.finalproject.Retrofit.RetrofitService;
import com.example.finalproject.databinding.ActivityNewsDetailBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailActivity extends AppCompatActivity {

    private ActivityNewsDetailBinding binding;
    private NewsDetailsAdapter newsDetailsAdapter;
    private ArrayList<Comment> allComments = new ArrayList<>();

    private NewsEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        event = getIntent().getParcelableExtra("news");

        newsDetailsAdapter = new NewsDetailsAdapter();
        binding.rv.setLayoutManager(new LinearLayoutManager(NewsDetailActivity.this));
        binding.rv.setAdapter(newsDetailsAdapter);

        RetrofitService service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);

        service.getDetails(event.getId()).enqueue(new Callback<NewsDetailsResponse>() {
            @Override
            public void onResponse(Call<NewsDetailsResponse> call, Response<NewsDetailsResponse> response) {
                NewsDetails details = response.body().data;

                newsDetailsAdapter.setLists(details);
            }

            @Override
            public void onFailure(Call<NewsDetailsResponse> call, Throwable t) {
                Log.d("NEUSPEX", "onFailure: " + t.getLocalizedMessage());
            }
        });

        service.getComments().enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {
                ArrayList<Comment> list = response.body().data;

                for(Comment c : list){
                    if(c.news == event.getId())
                        allComments.add(c);
                }

                newsDetailsAdapter.setComments(allComments, event.getComments_count(), event.getId());
            }

            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {

            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //share with any app from phone
        binding.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, event.getUrl());
                startActivity(Intent.createChooser(intent, "Pošalji preko: "));
            }
        });
    }

}