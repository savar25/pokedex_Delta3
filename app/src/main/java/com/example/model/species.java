package com.example.model;

import android.util.Log;

import com.example.network.RetrofitClientInstance;
import com.example.network.getSpecies;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class species {
    private static final String TAG = "species";

    @SerializedName("url")
    @Expose
    String url;

    String val;
    boolean flag;
    String s;

    public species(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
