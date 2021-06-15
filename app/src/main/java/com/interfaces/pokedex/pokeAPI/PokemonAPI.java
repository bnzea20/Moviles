package com.interfaces.pokedex.pokeAPI;

import com.interfaces.pokedex.pokeAPI.modelos.PokemonByIdResponse;
import com.interfaces.pokedex.pokeAPI.modelos.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {
    @GET("pokemon")
    Call<PokemonListResponse> getPokemonList();
    @GET("pokemon/{id}")
    Call<PokemonByIdResponse> getPokemonById(@Path("id") String id);
}
