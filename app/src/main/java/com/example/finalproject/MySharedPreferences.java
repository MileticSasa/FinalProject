package com.example.finalproject;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static MySharedPreferences instance;

    private MySharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static MySharedPreferences getInstance(Context context){
        if(instance == null){
            instance = new MySharedPreferences(context);
        }

        return instance;
    }

    public void addIsVoted(String commentId){
        editor.putBoolean(commentId, true);
        editor.apply();
    }

    public boolean getIsVoted(String commentId){
        return sharedPreferences.getBoolean(commentId, false);
    }
}
