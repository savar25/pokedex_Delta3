package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class outerType implements Serializable {

    @SerializedName("type")
    @Expose
    type type;

    public outerType(com.example.model.type type) {
        this.type = type;
    }

    public com.example.model.type getType() {
        return type;
    }

    public void setType(com.example.model.type type) {
        this.type = type;
    }
}
