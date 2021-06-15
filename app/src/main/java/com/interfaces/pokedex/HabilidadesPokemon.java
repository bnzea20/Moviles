package com.interfaces.pokedex;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class HabilidadesPokemon extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades_pokemon);
        WebView miWebview = findViewById(R.id.wvHab);
        miWebview.getSettings().setJavaScriptEnabled(true);
        miWebview.setWebViewClient(new WebViewClient());
        miWebview.loadUrl("http://pokedex.alimapp.com/pokemon.html");
    }
}