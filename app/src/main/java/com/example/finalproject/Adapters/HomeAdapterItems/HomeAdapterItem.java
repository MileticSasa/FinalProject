package com.example.finalproject.Adapters.HomeAdapterItems;

import com.example.finalproject.Adapters.HomeAdapter;

public interface HomeAdapterItem {

    int getType();
    int getPriority();
    void bind(HomeAdapter.MyHolder holder);
}
