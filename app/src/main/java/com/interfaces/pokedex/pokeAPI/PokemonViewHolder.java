package com.interfaces.pokedex.pokeAPI;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.interfaces.pokedex.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    TextView  NombrePokemon;
    Button AgregarPokemon;
    LinearLayout PokemonContainer;
    FirebaseAuth firebaseAuth;
    public PokemonViewHolder(@NonNull View v) {
        super(v);
        NombrePokemon = v.findViewById(R.id.NombrePokemon);
        PokemonContainer = v.findViewById(R.id.PokemonContainer);
        AgregarPokemon = v.findViewById(R.id.AgregarPokemon);
        AgregarPokemon.setVisibility(View.GONE);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            AgregarPokemon.setVisibility(View.VISIBLE);
        }


    }
}

