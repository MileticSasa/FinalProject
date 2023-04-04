package com.example.finalproject.Adapters.NewsDetailItems;

import com.example.finalproject.Adapters.HomeAdapter;
import com.example.finalproject.Adapters.NewsDetailsAdapter;

public interface DetailsAdapterItem {

    int getType();
    int getPriority();
    void bind(NewsDetailsAdapter.MyHolder holder);

}
