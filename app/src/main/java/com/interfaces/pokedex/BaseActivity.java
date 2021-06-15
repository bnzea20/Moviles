package com.interfaces.pokedex;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.interfaces.pokedex.pokeAPI.PokemonLoader;

public class BaseActivity extends AppCompatActivity {
    public PokemonLoader loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loader = new PokemonLoader();
    }
}


