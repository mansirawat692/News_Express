package com.example.newsexpress;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static  final String base_url="http://newsapi.org/v2/";
    private static ApiClient apiClient;
    public static Retrofit retrofit=null;

     public static Retrofit ApiClient(){
        if(retrofit==null)
            retrofit=new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

}
