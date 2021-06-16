package com.interfaces.pokedex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.interfaces.pokedex.pokeAPI.modelos.Pokemon;
import com.interfaces.pokedex.utils.Constante;

import java.util.ArrayList;
import java.util.List;

public class EquipoPokemon extends BaseActivity{

    Context context;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    ListView listView;
    //Button AgregarPokemones;

    private List<Pokemon> listEquipo = new ArrayList<Pokemon>();
    ArrayAdapter<Pokemon> arrayAdapterEquipo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pokemom_equipo);
        firebaseAuth = FirebaseAuth.getInstance();
        inicializarFirebase();
        listView = findViewById(R.id.listEquipoPokemon);

        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            ListarPokemones(user.getUid().toString());
        }
    }
    private void ListarPokemones(String usuario){
        databaseReference.child(usuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listEquipo.clear();
                for(DataSnapshot objSnaptShot : dataSnapshot.getChildren()){
                    Pokemon p = objSnaptShot.getValue(Pokemon.class);
                    listEquipo.add(p);

                    arrayAdapterEquipo = new ArrayAdapter<Pokemon>(EquipoPokemon.this, android.R.layout.simple_list_item_1, listEquipo);
                    listView.setAdapter(arrayAdapterEquipo);
                    listView.setClickable(true);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String idPokemon = (listView.getItemAtPosition(i).toString());
                            Intent intent = new Intent(EquipoPokemon.this, PokemonDetailActivity.class);
                            intent.putExtra(Constante.EXTRA_POKEMON_ID, idPokemon);
                            EquipoPokemon.this.startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

}
