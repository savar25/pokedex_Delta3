package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class variety {

    @SerializedName("pokemon")
    @Expose
    innerpoke Pokemon;

    public variety(innerpoke pokemon) {
        Pokemon = pokemon;
    }

    public innerpoke getPokemon() {
        return Pokemon;
    }

    public void setPokemon(innerpoke pokemon) {
        Pokemon = pokemon;
    }
}
