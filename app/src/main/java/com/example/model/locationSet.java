package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class locationSet {

    @SerializedName("results")
    @Expose
    List<location> locationList;

    public locationSet(List<location> locationList) {
        this.locationList = locationList;
    }

    public List<location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<location> locationList) {
        this.locationList = locationList;
    }
}
