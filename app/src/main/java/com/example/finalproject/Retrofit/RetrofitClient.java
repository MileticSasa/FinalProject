package com.example.finalproject.Retrofit;

import com.example.finalproject.Constants;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private Retrofit retrofit;
    private static RetrofitClient instance;

    public static RetrofitClient getInstance(){

        if(instance == null){
            instance = new RetrofitClient();
        }

        return instance;
    }

    public Retrofit getRetrofit(){
        if(retrofit == null){

            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    String credentials = Credentials.basic("kursqa@cubes.edu.rs", "cubesqa");
                    return response.request().newBuilder().header("Authorization", credentials).build();
                }
            });

            retrofit = new Retrofit.Builder().client(client.build()).baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }

    private RetrofitClient(){

    }
}
