package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class outerStat implements Serializable {

    @SerializedName("base_stat")
    @Expose
    Integer statVal;

    @SerializedName("stat")
    @Expose
    stat Stat;

    public outerStat(Integer statVal, stat stat) {
        this.statVal = statVal;
        Stat = stat;
    }


    public Integer getStatVal() {
        return statVal;
    }

    public void setStatVal(Integer statVal) {
        this.statVal = statVal;
    }

    public stat getStat() {
        return Stat;
    }

    public void setStat(stat stat) {
        Stat = stat;
    }
}
