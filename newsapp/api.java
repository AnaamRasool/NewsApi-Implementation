package com.wowvio.newsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface api {

    @GET("top-headlines")
    Call<model> getpost(@Query("sources") String source,@Query("apiKey") String apiKey);


}
