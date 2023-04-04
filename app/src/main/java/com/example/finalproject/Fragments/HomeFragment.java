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
import com.example.finalproject.CategoryNameListener;
import com.example.finalproject.FragmentChangeListener;
import com.example.finalproject.Adapters.HomeAdapter;
import com.example.finalproject.Model.Category;
import com.example.finalproject.Model.CategoryNews;
import com.example.finalproject.Model.EventsLists;
import com.example.finalproject.R;
import com.example.finalproject.Response.CategoriesResponse;
import com.example.finalproject.Response.CompeteEventResponse;
import com.example.finalproject.Retrofit.RetrofitClient;
import com.example.finalproject.Retrofit.RetrofitService;
import com.example.finalproject.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements CategoryNameListener {

    private FragmentHomeBinding binding;
    private HomeAdapter adapter;
    private EventsLists lists;
    RetrofitService service;
    private AllCategoriesAdapter adapter2;
    private boolean isMenuIconClicked = false;

    private FragmentChangeListener listener;

    public HomeFragment(FragmentChangeListener listener) {
        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);
        setHome();

        showAllCategoriesList();

        service.getCategories().enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                ArrayList<Category> list = response.body().data;

                binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Naslovna")); //first item
                for(Category category : list){
                    binding.tabLayout.addTab(binding.tabLayout.newTab().setText(category.getName()));
                }

                handleCategoryClick();
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {

            }
        });

        binding.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isMenuIconClicked) {
                    binding.recyclerView.setVisibility(View.GONE);
                    binding.tabLayout.setVisibility(View.GONE);
                    MainActivity.hideBottomBar();
                    binding.rv2.setVisibility(View.VISIBLE);
                    binding.ivMenu.setImageResource(R.drawable.ic_close);
                    isMenuIconClicked = true;
                }
                else {
                    binding.recyclerView.setVisibility(View.VISIBLE);
                    binding.tabLayout.setVisibility(View.VISIBLE);
                    MainActivity.showBottomBar();
                    binding.rv2.setVisibility(View.GONE);
                    showAllCategoriesList();
                    binding.ivMenu.setImageResource(R.drawable.menu_icon);
                    isMenuIconClicked = false;
                }
            }
        });
    }

    private void handleCategoryClick() {

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    adapter.addEvents(lists, null);
                } else {
                    for (CategoryNews news : lists.getCategory()) {
                        if (news.getTitle().equalsIgnoreCase((String) tab.getText())) {
                            adapter.addEvents(null, news.getNews());
                        }
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
                adapter2  = new AllCategoriesAdapter(response.body().data, HomeFragment.this::onItemClick);
                binding.rv2.addItemDecoration(decoration);
                binding.rv2.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {

            }
        });
    }

    private void setHome(){
        binding.pb.setVisibility(View.VISIBLE);

        service.getEvent().enqueue(new Callback<CompeteEventResponse>() {
            @Override
            public void onResponse(Call<CompeteEventResponse> call, Response<CompeteEventResponse> response) {
                lists = response.body().getData();

                adapter = new HomeAdapter(getContext());
                //LinearLayoutManager manager = new LinearLayoutManager(getContext());
                adapter.addEvents(lists, null);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerView.setAdapter(adapter);

                binding.tabLayout.setVisibility(View.VISIBLE);

                binding.pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CompeteEventResponse> call, Throwable t) {
                binding.pb.setVisibility(View.GONE);
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

        listener.onFragmentChange(4); //update UI for active fragment

        MainActivity.showBottomBar();
    }
}