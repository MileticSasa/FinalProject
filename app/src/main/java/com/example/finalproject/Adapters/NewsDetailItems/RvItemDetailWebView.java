package com.example.finalproject.Adapters.NewsDetailItems;

import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.example.finalproject.Adapters.NewsDetailsAdapter;
import com.example.finalproject.databinding.RvItemDetailWebViewBinding;

public class RvItemDetailWebView implements DetailsAdapterItem{

    private String url;

    public RvItemDetailWebView(String url) {
        this.url = url;
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public void bind(NewsDetailsAdapter.MyHolder holder) {
        RvItemDetailWebViewBinding binding = (RvItemDetailWebViewBinding) holder.binding;

        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadUrl(url);
        WebSettings settings = binding.webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}
