package com.interfaces.pokedex;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.interfaces.pokedex.pokeAPI.PokemonAdapter;
import com.interfaces.pokedex.pokeAPI.PokemonLoader;
import com.interfaces.pokedex.pokeAPI.modelos.Pokemon;
import com.interfaces.pokedex.pokeAPI.modelos.PokemonListResponse;
import com.interfaces.pokedex.utils.Constante;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pokemon);
        final RecyclerView rvPokemon  = findViewById(R.id.rvPokemon);
        Call<PokemonListResponse> call = loader.getPokemonList();
        call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                List<Pokemon> pokemonList = response.body().getPokemonList();
                PokemonAdapter adapter = new PokemonAdapter(pokemonList, PokemonActivity.this);
                rvPokemon.setAdapter(adapter);
                rvPokemon.setHasFixedSize(true);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(PokemonActivity.this);
                rvPokemon.setLayoutManager(manager);
            }
            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                Log.e(Constante.DEBUG_POKEMON, t.getMessage());

            }
        });
    }
}
