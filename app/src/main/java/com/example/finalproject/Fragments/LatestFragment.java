package com.example.finalproject.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproject.Activities.MainActivity;
import com.example.finalproject.Adapters.AllCategoriesAdapter;
import com.example.finalproject.Adapters.EventAdapter;
import com.example.finalproject.CategoryNameListener;
import com.example.finalproject.FragmentChangeListener;
import com.example.finalproject.Model.NewsEvent;
import com.example.finalproject.R;
import com.example.finalproject.Response.CategoriesResponse;
import com.example.finalproject.Response.LatestResponse;
import com.example.finalproject.Retrofit.RetrofitClient;
import com.example.finalproject.Retrofit.RetrofitService;
import com.example.finalproject.databinding.FragmentLatestBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestFragment extends Fragment implements CategoryNameListener {

    private FragmentLatestBinding binding;
    private EventAdapter adapter;
    private AllCategoriesAdapter adapter2;
    private boolean isMenuIconClicked = false;

    FragmentChangeListener listener;

    public LatestFragment(FragmentChangeListener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLatestBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new EventAdapter(getContext());

        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(adapter);

        getAndShowNews();

        showAllCategoriesList();

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
                adapter2  = new AllCategoriesAdapter(response.body().data, LatestFragment.this::onItemClick);
                binding.rv2.addItemDecoration(decoration);
                binding.rv2.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {

            }
        });
    }

    private void getAndShowNews() {
        RetrofitService service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);

        binding.progressBar.setVisibility(View.VISIBLE);

        service.getLatest(1).enqueue(new Callback<LatestResponse>() {
            @Override
            public void onResponse(Call<LatestResponse> call, Response<LatestResponse> response) {

                ArrayList<NewsEvent> list = response.body().data.getNews();

                adapter.setList(list);

                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LatestResponse> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onItemClick(String name) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();

        listener.onFragmentChange(4);

        MainActivity.showBottomBar();
    }

}