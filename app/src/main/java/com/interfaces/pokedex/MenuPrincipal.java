package com.interfaces.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MenuPrincipal extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FloatingActionButton botonFlotante;
    private Button btnHabilidades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        botonFlotante=(FloatingActionButton) findViewById(R.id.fab);
        firebaseAuth = FirebaseAuth.getInstance();
        btnHabilidades= (Button) findViewById(R.id.buttonHabilidades);
        btnHabilidades.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(MenuPrincipal.this, HabilidadesPokemon.class);
                startActivity(i);
            }
        });
        botonFlotante.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MenuPrincipal.this,"Se cerró la sesión", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MenuPrincipal.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void Siguiente(View view){
        Intent siguiente1 =new Intent(this,MainActivity.class );
        startActivity(siguiente1);
    }
    @Override
    public void onBackPressed() {
        // hacer nada
    }
}