package com.example.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    public static Retrofit retrofit;
    public static String BASE_URL="https://pokeapi.co/api/v2/";

    public static Retrofit getRetrofit() {
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
