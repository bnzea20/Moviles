package com.interfaces.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        //Pregunta si ya existe un usuario logueado
        if (user != null) {
            irPrincipal();
            MainActivity.this.finish();
        }


    }
    public void Siguiente(View view){
        Intent siguiente1 =new Intent(this, Registro.class );
        startActivity(siguiente1);
    }
    public void Siguiente2(View view){
        Intent siguiente2 =new Intent(this, InicioSesion.class );
        startActivity(siguiente2);
    }
    public void Siguiente3(View view){
        Intent siguiente3 =new Intent(this, PokemonActivity.class );
        startActivity(siguiente3);
    }
    private void irPrincipal(){
        Intent principal =new Intent(this,MenuPrincipal.class );
        startActivity(principal);
        Toast.makeText(this,"Bienvenido de nuevo a Pokédex Móvil", Toast.LENGTH_LONG).show();
    }
}