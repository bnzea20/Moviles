package com.interfaces.pokedex.pokeAPI.modelos;

import com.google.gson.annotations.SerializedName;

public class Sprite {
    @SerializedName("front_default")
    private String image;

    public String getImage() {
        return image;
    }
}
