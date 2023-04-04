package com.example.finalproject.Retrofit;

import com.example.finalproject.Model.Comment;
import com.example.finalproject.Model.CommentVote;
import com.example.finalproject.Model.NewsDetails;
import com.example.finalproject.Response.CategoriesResponse;
import com.example.finalproject.Response.CommentResponse;
import com.example.finalproject.Response.CommentsResponse;
import com.example.finalproject.Response.CompeteEventResponse;
import com.example.finalproject.Response.HoroscopeResponse;
import com.example.finalproject.Response.LatestResponse;
import com.example.finalproject.Response.NewsDetailsResponse;
import com.example.finalproject.Response.SearchResponse;
import com.example.finalproject.Response.VideosResponse;
import com.example.finalproject.Response.VoteResponse;
import com.example.finalproject.Response.WeatherResponse;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("homepage")
    Call<CompeteEventResponse> getEvent();

    @GET("categories")
    Call<CategoriesResponse> getCategories();

    @GET("latest")
    Call<LatestResponse> getLatest(@Query("current_page") int number);

    @GET("search")
    Call<SearchResponse> getByCategory(@Query("search_parameter") String categoryName);

    @GET("videos")
    Call<VideosResponse> getVideos();

    @GET("comments")
    Call<CommentsResponse> getComments();

    @GET("newsdetails")
    Call<NewsDetailsResponse> getDetails(@Query("id") int id);


    @POST("commentinsert")
    Call<CommentsResponse> addComment(@Body Comment comment);

    @POST("commentvote")
    Call<VoteResponse> addVote(@Body CommentVote commentVote);

    @GET("horoscope")
    Call<HoroscopeResponse> getHoroscope();

    @GET("weather")
    Call<WeatherResponse> getWeather();

}
