package com.example.network;

import com.example.model.pokedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface getsecondInstance {

    @GET("https://pokeapi.co/api/v2/pokedex/{id}")
    Call<pokedex> getPokedexes(@Path("id") int ID);
}
