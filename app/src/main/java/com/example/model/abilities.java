package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class abilities implements Serializable {
    @SerializedName("name")
    @Expose
    String abiName;

    @SerializedName("url")
    @Expose
    String abiUrl;

    public abilities(String abiName, String abiUrl) {
        this.abiName = changeLetter(abiName);
        this.abiUrl = abiUrl;
    }

    public String getAbiName() {
        return abiName;
    }

    public void setAbiName(String abiName) {
        this.abiName = abiName;
    }

    public String getAbiUrl() {
        return abiUrl;
    }

    public void setAbiUrl(String abiUrl) {
        this.abiUrl = abiUrl;
    }

    @Override
    public String toString() {
        return "abilities{" +
                "abiName='" + abiName + '\'' +
                ", abiUrl='" + abiUrl + '\'' +
                '}';
    }

    public String changeLetter(String s){
        String sub=s.substring(0,1);
        sub.toUpperCase();
        s=sub+s.substring(1);
        return s;
    }
}
