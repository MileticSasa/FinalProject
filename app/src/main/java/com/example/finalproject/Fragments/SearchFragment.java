package com.example.finalproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.Activities.MainActivity;
import com.example.finalproject.Adapters.AllCategoriesAdapter;
import com.example.finalproject.Adapters.EventAdapter;
import com.example.finalproject.CategoryNameListener;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.R;
import com.example.finalproject.Response.CategoriesResponse;
import com.example.finalproject.Response.LatestResponse;
import com.example.finalproject.Response.SearchResponse;
import com.example.finalproject.Retrofit.RetrofitClient;
import com.example.finalproject.Retrofit.RetrofitService;
import com.example.finalproject.databinding.FragmentSearchBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment implements CategoryNameListener {

    private FragmentSearchBinding binding;
    private EventAdapter adapter;
    private ArrayList<NewsEvent> events;
    private AllCategoriesAdapter adapter2;
    private boolean isMenuIconClicked = false;

    private String categoryName;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            categoryName = bundle.getString("name");
        }

        adapter = new EventAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.rv.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(binding.rv.getContext(), manager.getOrientation());
        binding.rv.addItemDecoration(decoration);
        binding.rv.setAdapter(adapter);

        showAllCategoriesList();

        //setting list that was sent from another fragment in rv
        if(categoryName != null){
            getAndShowNews(categoryName);
        }

        //setting data taken from searchBar
        binding.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.et.getText().length() > 0){
                    getAndShowNews(binding.et.getText().toString());
                }
            }
        });


        binding.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isMenuIconClicked) {
                    binding.rv.setVisibility(View.GONE);
                    MainActivity.hideBottomBar();
                    binding.rv2.setVisibility(View.VISIBLE);
                    binding.ivMenu.setImageResource(R.drawable.ic_close);
                    isMenuIconClicked = true;
                }
                else {
                    binding.rv.setVisibility(View.VISIBLE);
                    MainActivity.showBottomBar();
                    binding.rv2.setVisibility(View.GONE);
                    binding.ivMenu.setImageResource(R.drawable.menu_icon);
                    isMenuIconClicked = false;
                }
            }
        });
    }

    private void getAndShowNews(String categoryName){
        RetrofitService service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);

        binding.progressBar.setVisibility(View.VISIBLE);

        service.getByCategory(categoryName).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                events = response.body().data.news;

                adapter.setList(events);

                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showAllCategoriesList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.rv2.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(
                binding.rv2.getContext(), manager.getOrientation());

        RetrofitService service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);
        service.getCategories().enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                adapter2  = new AllCategoriesAdapter(response.body().data, SearchFragment.this::onItemClick);
                binding.rv2.addItemDecoration(decoration);
                binding.rv2.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(String name) {

        getAndShowNews(name);
        binding.rv2.setVisibility(View.GONE);
        binding.rv.setVisibility(View.VISIBLE);
        binding.ivMenu.setImageResource(R.drawable.menu_icon);
        isMenuIconClicked = false;

        MainActivity.showBottomBar();
    }
}