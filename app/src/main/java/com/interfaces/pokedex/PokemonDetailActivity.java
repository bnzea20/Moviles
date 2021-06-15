package com.interfaces.pokedex;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.interfaces.pokedex.pokeAPI.PokemonLoader;
import com.interfaces.pokedex.pokeAPI.modelos.Abilities;
import com.interfaces.pokedex.pokeAPI.modelos.Ability;
import com.interfaces.pokedex.pokeAPI.modelos.Pokemon;
import com.interfaces.pokedex.pokeAPI.modelos.PokemonByIdResponse;
import com.interfaces.pokedex.pokeAPI.modelos.Type;
import com.interfaces.pokedex.pokeAPI.modelos.Types;
import com.interfaces.pokedex.utils.Constante;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailActivity extends BaseActivity {

    TextView tvPokeTitle, tvPokeXp, tvPokeAbilities, tvPokeHeight, tvPokeWeight, tvPokeType;
    ImageView ivPokeSprite;
    RecyclerView rvPokeGames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        tvPokeTitle = findViewById(R.id.tvPokeTitle);
        tvPokeHeight = findViewById(R.id.tvPokeHeight);
        tvPokeWeight = findViewById(R.id.tvPokeWeight);
        tvPokeXp = findViewById(R.id.tvPokeXp);
        tvPokeType = findViewById(R.id.tvPokeTypes);
        tvPokeAbilities = findViewById(R.id.tvPokeAbilities);
        ivPokeSprite = findViewById(R.id.ivPokeSprite);

        String pokemonId = getIntent().getStringExtra(Constante.EXTRA_POKEMON_ID);
        Call<PokemonByIdResponse> call = loader.getPokemonById(pokemonId);
        call.enqueue(new Callback<PokemonByIdResponse>() {
            @Override
            public void onResponse(Call<PokemonByIdResponse> call, Response<PokemonByIdResponse> response) {
                tvPokeTitle.setText(response.body().getId() + " - " + response.body().getName());
                tvPokeHeight.setText("Altura: " + response.body().getHeight() + "0 cm");
                tvPokeWeight.setText("Peso: " + response.body().getWeight() + "00 gr");
                tvPokeXp.setText("XP base: " + response.body().getBaseExperience());

                Glide.with(PokemonDetailActivity.this).load(response.body().getSprites().getImage()).into(ivPokeSprite);

                List<Type> typeList = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                for(Types types : response.body().getTypes()){
                    typeList.add(types.getType());
                }
                for(Type type : typeList){
                    sb.append(type.getName() +  "\n");
                }
                tvPokeType.setText(sb.toString());

                List<Ability> abilityList = new ArrayList<>();
                StringBuilder sb1 = new StringBuilder();
                for(Abilities abilities : response.body().getAbilities()){
                    abilityList.add(abilities.getAbility());
                }
                for(Ability ability : abilityList){
                    sb1.append(ability.getName() +  "\n");
                }
                tvPokeAbilities.setText(sb1.toString());
            }

            @Override
            public void onFailure(Call<PokemonByIdResponse> call, Throwable t) {
                Log.e(Constante.DEBUG_POKEMON, t.getMessage());
            }
        });

    }
}
