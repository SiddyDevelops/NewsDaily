package com.siddydevelops.newsdaily.api;

import com.siddydevelops.newsdaily.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<News> getNews(
            @Query("country") String country,
            @Query("pageSize") int pageSize,
            @Query("apikey") String apikey
    );

    @GET("top-headlines")
    Call<News> getCategoryNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("apikey") String apikey
    );

}
