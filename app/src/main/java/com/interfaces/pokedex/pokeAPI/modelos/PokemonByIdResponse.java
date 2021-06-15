package com.interfaces.pokedex.pokeAPI.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonByIdResponse {
    private List<Abilities> abilities;
    private List<Types> types;
    @SerializedName("height")
    private int height;
    @SerializedName("weight")
    private int weight;
    @SerializedName("base_experience")
    private int baseExperience;
    private String name;
    private int id;
    private Sprite sprites;

    public List<Types> getTypes() {
        return types;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Sprite getSprites() {
        return sprites;
    }
}
