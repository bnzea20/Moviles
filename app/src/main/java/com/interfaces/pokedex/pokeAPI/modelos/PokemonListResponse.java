package com.interfaces.pokedex.pokeAPI.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonListResponse {

    //Esta clase nos devuelve una respuesta

    private int count;
    private String next;
    private String previous;
    @SerializedName("results")
    private List<Pokemon> pokemonList;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

}
