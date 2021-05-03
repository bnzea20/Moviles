package com.interfaces.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Siguiente(View view){
        Intent siguiente1 =new Intent(this, IniciarSesion.class );
        startActivity(siguiente1);
    }
    public void Siguiente2(View view){
        Intent siguiente2 =new Intent(this,pantalla3.class );
        startActivity(siguiente2);
    }
    public void Siguiente3(View view){
        Intent siguiente3 =new Intent(this,Navegacion.class );
        startActivity(siguiente3);
    }
}