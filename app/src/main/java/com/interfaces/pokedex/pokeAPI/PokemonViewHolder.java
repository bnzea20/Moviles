package com.interfaces.pokedex.pokeAPI;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.interfaces.pokedex.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    TextView  NombrePokemon;
    LinearLayout PokemonContainer;
    public PokemonViewHolder(@NonNull View v) {
        super(v);
        NombrePokemon = v.findViewById(R.id.NombrePokemon);
        PokemonContainer = v.findViewById(R.id.PokemonContainer);
    }
}

