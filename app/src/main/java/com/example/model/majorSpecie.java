package com.example.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class majorSpecie {
    private static final String TAG = "majorSpecie";
    @SerializedName("varieties")
    @Expose
    List<variety> varieties;

    public majorSpecie(List<variety> varieties)
    {
        Log.d(TAG, "majorSpecie: ");
        this.varieties = varieties;
    }

    public List<variety> getVarieties() {
        return varieties;
    }

    public void setVarieties(List<variety> varieties) {
        this.varieties = varieties;
    }
}
