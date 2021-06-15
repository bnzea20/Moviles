package com.interfaces.pokedex.pokeAPI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.interfaces.pokedex.PokemonDetailActivity;
import com.interfaces.pokedex.R;
import com.interfaces.pokedex.pokeAPI.modelos.Pokemon;
import com.interfaces.pokedex.utils.Constante;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {
    List<Pokemon> pokemonList;
    Context context;


    public PokemonAdapter(List<Pokemon> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent,false);

        return new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, final int i) {
        holder.NombrePokemon.setText(pokemonList.get(i).getName());

        holder.PokemonContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokemonId = pokemonList.get(i).getName();
                Intent intent = new Intent(context, PokemonDetailActivity.class);
                intent.putExtra(Constante.EXTRA_POKEMON_ID, pokemonId);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

}
