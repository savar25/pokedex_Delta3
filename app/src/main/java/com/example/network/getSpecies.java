package com.example.network;

import com.example.model.majorSpecie;
import com.example.model.variety;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface getSpecies {
    @GET("pokemon-species/{id}")
    Call<majorSpecie> getSpecieVariety(@Path("id") Integer ID);
}
