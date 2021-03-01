package com.example.newsexpress;

import com.example.newsexpress.model.headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInteface {

    @GET("top-headlines")
    Call<headlines> getheadlines(
            @Query("country") String country,
            @Query("apiKey") String apikey
    );

    @GET("everything")
    Call<headlines> getSpecificData(
            @Query("query") String query,
            @Query("apiKey") String apiKey
    );
}
