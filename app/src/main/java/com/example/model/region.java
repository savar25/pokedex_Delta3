package com.example.model;

import android.widget.Toast;

import com.example.myapplication.list;
import com.example.network.RetrofitClientInstance;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class region {

   @SerializedName("name")
   @Expose
   String name;

   @SerializedName("url")
   @Expose
   String url;

    public region(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

