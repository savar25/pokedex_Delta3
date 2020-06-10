package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class entry {
    @SerializedName("pokemon_species")
    @Expose
    species species1;

    public entry(species species1) {
        this.species1 = species1;
    }

    public species getSpecies1() {
        return species1;
    }

    public void setSpecies1(species species1) {
        this.species1 = species1;

    }}
