package com.example.network;

import com.example.model.item;
import com.example.model.itemSet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getItemInstance {

    @GET("item")
    Call<itemSet> getItems();
}
