package com.interfaces.pokedex.pokeAPI;

import java.util.ArrayList;

public class PokemonRespuesta {

    private ArrayList<Pokemon> results;

    //Contendra objetos

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
