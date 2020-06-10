package com.example.model;

import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.regionList;
import com.example.network.RetrofitClientInstance;
import com.example.network.getSpecies;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class innerpoke {
    private static final String TAG = "innerpoke";
    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("url")
    @Expose
    String url;

    public innerpoke(String name, String url) {
        Log.d(TAG, "innerpoke: ");
        this.name = name;
        this.url = url;
    }

    public String getName()
    {
        Log.d(TAG, "getName: ");
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


