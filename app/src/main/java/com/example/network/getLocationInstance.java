package com.example.network;

import com.example.model.locationSet;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getLocationInstance {

    @GET("location")
    Call<locationSet> getLocations();
}
