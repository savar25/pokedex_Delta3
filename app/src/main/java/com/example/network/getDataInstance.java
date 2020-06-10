package com.example.network;

import com.example.model.pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface getDataInstance {

    @GET("pokemon/{id}")
    Call<pokemon> getInfo(@Path("id") Integer ID);
}
