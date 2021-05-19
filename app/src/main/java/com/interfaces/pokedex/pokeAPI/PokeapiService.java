package com.interfaces.pokedex.pokeAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeapiService {

    @GET("pokemon") //parte de la url que falta para acceder a los pokemones
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);

    //<Clase que recibira la respuesta>
}
