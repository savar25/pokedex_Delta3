package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class outerAbility implements Serializable {
    @SerializedName("ability")
    @Expose
    abilities abi;

    public outerAbility(abilities abi) {
        this.abi = abi;
    }

    public void setAbi(abilities abi) {
        this.abi = abi;
    }

    public abilities getAbi() {
        return abi;
    }
}
