package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class pokedex {

    @SerializedName("pokemon_entries")
    @Expose
    List<entry> pokemon_entry;

    @SerializedName("region")
    @Expose
    region Region;

    @SerializedName("name")
    @Expose
    String name;

    public boolean expandable=false;


    public pokedex(List<entry> pokemon_entry, region region, String name) {
        this.pokemon_entry = pokemon_entry;
        Region = region;
        this.name = name;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<entry> getPokemon_entry() {
        return pokemon_entry;
    }

    public void setPokemon_entry(List<entry> pokemon_entry) {
        this.pokemon_entry = pokemon_entry;
    }

    public region getRegion() {
        return Region;
    }

    public void setRegion(region region) {
        Region = region;
    }


    public void callEntry(){

    }
}
