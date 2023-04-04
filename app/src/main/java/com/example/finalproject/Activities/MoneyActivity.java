package com.example.finalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.example.finalproject.R;
import com.example.finalproject.databinding.ActivityMoneyBinding;

public class MoneyActivity extends AppCompatActivity {

    private ActivityMoneyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoneyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WebViewClient client = new WebViewClient();
        binding.wv.setWebViewClient(client);
        binding.wv.loadUrl("https://www.kursna-lista.com/");
        WebSettings settings = binding.wv.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}