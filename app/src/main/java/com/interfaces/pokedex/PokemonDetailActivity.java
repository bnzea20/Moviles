package com.interfaces.pokedex;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private TextView tvPokeTitle, tvPokeXp, tvPokeAbilities, tvPokeHeight, tvPokeWeight, tvPokeType, tvPokeAviso, tvPokeNaturaleza, tvPokeHabilidades;
    ImageView ivPokeSprite;
    RecyclerView rvPokeGames;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        tvPokeAviso =findViewById(R.id.tvPokeAviso);
        tvPokeNaturaleza =findViewById(R.id.tvPokeNaturalezas);
        tvPokeHabilidades =findViewById(R.id.tvPokeHabilidades);
        tvPokeTitle = findViewById(R.id.tvPokeTitle);
        tvPokeHeight = findViewById(R.id.tvPokeHeight);
        tvPokeWeight = findViewById(R.id.tvPokeWeight);
        tvPokeXp = findViewById(R.id.tvPokeXp);
        tvPokeType = findViewById(R.id.tvPokeTypes);
        tvPokeAbilities = findViewById(R.id.tvPokeAbilities);
        ivPokeSprite = findViewById(R.id.ivPokeSprite);
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        //Pregunta si ya existe un usuario logueado para ofrecer opciones adicionales
        if (user != null) {
            habilitarFuncionesAdicionales(tvPokeAviso, tvPokeNaturaleza, tvPokeHabilidades, tvPokeTitle, tvPokeHeight,tvPokeWeight,tvPokeXp,tvPokeType,tvPokeAbilities);

        }

        String pokemonId = getIntent().getStringExtra(Constante.EXTRA_POKEMON_ID);
        Call<PokemonByIdResponse> call = loader.getPokemonById(pokemonId);
        call.enqueue(new Callback<PokemonByIdResponse>() {
            @Override
            public void onResponse(Call<PokemonByIdResponse> call, Response<PokemonByIdResponse> response) {
                tvPokeTitle.setText(response.body().getId() + " - " + upperCaseFirst(response.body().getName()));
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
                    sb.append(upperCaseFirst(type.getName()) +  "\n");
                }
                tvPokeType.setText(sb.toString());

                List<Ability> abilityList = new ArrayList<>();
                StringBuilder sb1 = new StringBuilder();
                for(Abilities abilities : response.body().getAbilities()){
                    abilityList.add(abilities.getAbility());
                }
                for(Ability ability : abilityList){
                    sb1.append(upperCaseFirst(ability.getName()) +  "\n");
                }
                tvPokeAbilities.setText(sb1.toString());
            }

            @Override
            public void onFailure(Call<PokemonByIdResponse> call, Throwable t) {
                Log.e(Constante.DEBUG_POKEMON, t.getMessage());
            }
            //Metodo que convierte la primera letra de una cadena a mayuscula
            public String upperCaseFirst(String val) {
                char[] arr = val.toCharArray();
                arr[0] = Character.toUpperCase(arr[0]);
                return new String(arr);
            }
        });

    }
    private void habilitarFuncionesAdicionales(TextView a, TextView b, TextView c, TextView d, TextView e, TextView f, TextView g, TextView h, TextView i) {
        a.setVisibility(View.GONE);
        b.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
        e.setVisibility(View.VISIBLE);
        f.setVisibility(View.VISIBLE);
        g.setVisibility(View.VISIBLE);
        h.setVisibility(View.VISIBLE);
        i.setVisibility(View.VISIBLE);
    }
}
