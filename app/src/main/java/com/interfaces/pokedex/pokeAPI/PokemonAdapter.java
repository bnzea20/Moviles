package com.interfaces.pokedex.pokeAPI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.interfaces.pokedex.PokemonDetailActivity;
import com.interfaces.pokedex.R;
import com.interfaces.pokedex.pokeAPI.modelos.Pokemon;
import com.interfaces.pokedex.utils.Constante;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {
    List<Pokemon> pokemonList;
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private String obtenerUsuario() {
        String usuario="Poke";
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            usuario = user.getUid();
            return usuario;
        }
        return usuario;
    }


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

        //FUNCIONALIDADES Y BOTON PARA AÑADIR LOS POKEMONES AL EQUIPO


        holder.AgregarPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicializarFirebase();
                String finalUsuario = obtenerUsuario();
                if (finalUsuario.equals("Poke")){

                }else {
                    Pokemon p = new Pokemon();
                    p.setName(pokemonList.get(i).getName());
                    databaseReference.child(finalUsuario).child(p.getName()).setValue(p);
                    Toast.makeText(context,"Se agregó el pokémon al equipo",Toast.LENGTH_LONG);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

}
