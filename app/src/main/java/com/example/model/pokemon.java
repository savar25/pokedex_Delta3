package com.example.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class pokemon implements Serializable {
    private static final String TAG = "pokemon";
    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("sprites")
    @Expose
    Sprites sprites;

    @SerializedName("abilities")
    @Expose
    List<outerAbility>ability;

    @SerializedName("types")
    @Expose
    List<outerType> outerTypeList;

    @SerializedName("stats")
    @Expose
    List<outerStat> outerStatList;

    @SerializedName("height")
    @Expose
    Integer height;

    @SerializedName("weight")
    @Expose
    Integer weight;

    @SerializedName("base_experience")
    @Expose
    Integer baseExp;

    public boolean expandable=false;

    public pokemon(String name, Sprites sprites, List<outerAbility> ability, List<outerType> outerTypeList, List<outerStat> outerStatList, Integer height, Integer weight, Integer baseExp) {
        this.name = name;
        this.sprites = sprites;
        this.ability = ability;
        this.outerTypeList = outerTypeList;
        this.outerStatList = outerStatList;
        this.height = height;
        this.weight = weight;
        this.baseExp = baseExp;
    }

    public List<outerStat> getOuterStatList() {
        return outerStatList;
    }

    public void setOuterStatList(List<outerStat> outerStatList) {
        this.outerStatList = outerStatList;
    }

    public List<outerType> getOuterTypeList() {
        return outerTypeList;
    }

    public void setOuterTypeList(List<outerType> outerTypeList) {
        this.outerTypeList = outerTypeList;
    }

    public boolean isExpandable() {
        Log.d(TAG, "isExpandable: "+expandable);
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public String getName() {
        return name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBaseExp() {
        return baseExp;
    }

    public void setBaseExp(Integer baseExp) {
        this.baseExp = baseExp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public List<outerAbility> getAbility() {
        return ability;
    }

    public void setAbility(List<outerAbility> ability) {
        this.ability = ability;
    }

    public String changeLetter(String s){
        String sub=s.substring(0,1);

        sub.toUpperCase();
        s=sub+s.substring(1);
        Log.d(TAG, "changeLetter: "+s);
        return s;
    }
}
