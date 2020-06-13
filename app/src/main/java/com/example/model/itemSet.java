package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class itemSet {

    @SerializedName("results")
    @Expose
    List<item> itemList;

    public itemSet(List<item> itemList) {
        this.itemList = itemList;
    }

    public List<item> getItemList() {
        return itemList;
    }

    public void setItemList(List<item> itemList) {
        this.itemList = itemList;
    }
}
