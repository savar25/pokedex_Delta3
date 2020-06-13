package com.example.model;

import java.io.Serializable;
import java.util.List;

public class favListItem implements Serializable {

    String name;
    String imgUrl;
    String fimg,fsimg,bimg,bsimg;

    List<outerAbility> abilityList;

    boolean expandable=false;

    public favListItem(String name, String imgUrl, String fimg, String fsimg, String bimg, String bsimg, List<outerAbility> abilityList) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.fimg = fimg;
        this.fsimg = fsimg;
        this.bimg = bimg;
        this.bsimg = bsimg;
        this.abilityList = abilityList;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public String getFimg() {
        return fimg;
    }

    public void setFimg(String fimg) {
        this.fimg = fimg;
    }

    public String getFsimg() {
        return fsimg;
    }

    public void setFsimg(String fsimg) {
        this.fsimg = fsimg;
    }

    public String getBimg() {
        return bimg;
    }

    public void setBimg(String bimg) {
        this.bimg = bimg;
    }

    public String getBsimg() {
        return bsimg;
    }

    public void setBsimg(String bsimg) {
        this.bsimg = bsimg;
    }

    public List<outerAbility> getAbilityList() {
        return abilityList;
    }

    public void setAbilityList(List<outerAbility> abilityList) {
        this.abilityList = abilityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
