package com.example.finalproject.Adapters.NewsDetailItems;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.Adapters.NewsDetailsAdapter;
import com.example.finalproject.databinding.RvItemDetailShareBinding;

public class RvItemDetailShare implements DetailsAdapterItem{

    private String url;
    public RvItemDetailShare(String url) {
        this.url = url;
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public void bind(NewsDetailsAdapter.MyHolder holder) {
        RvItemDetailShareBinding binding = (RvItemDetailShareBinding) holder.binding;

        binding.ivFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, url);
                intent.setPackage("com.facebook.katana");
                holder.itemView.getContext().startActivity(Intent.createChooser(intent, "Podeli"));
            }
        });

        binding.ivV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, url);
                intent.setPackage("com.viber.voip");
                holder.itemView.getContext().startActivity(Intent.createChooser(intent, "Podeli"));
            }
        });

        binding.ivWp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, url);
                intent.setPackage("com.whatsapp");
                holder.itemView.getContext().startActivity(Intent.createChooser(intent, "Podeli"));
            }
        });

        binding.ivIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, url);
                intent.setPackage("com.instagram.android");
                holder.itemView.getContext().startActivity(Intent.createChooser(intent, "Podeli"));
            }
        });

        binding.ivTw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, url);
                intent.setPackage("com.twitter.android");
                holder.itemView.getContext().startActivity(Intent.createChooser(intent, "Podeli"));
            }
        });
    }
}
