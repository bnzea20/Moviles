package com.interfaces.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private TextView Txtcorreo, TxtUID;
    private ImageView foto;
    private FloatingActionButton botonFlotante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Txtcorreo=(TextView)findViewById(R.id.txtCorreo);
        TxtUID=(TextView)findViewById(R.id.txtUID);
        foto=(ImageView)findViewById(R.id.imageView4);
        botonFlotante=(FloatingActionButton) findViewById(R.id.fab2);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            Txtcorreo.setText("Correo: "+user.getEmail().toString().trim());
            TxtUID.setText("UID: "+user.getUid().toString().trim());
            Glide.with(this).load(user.getPhotoUrl()).into(foto);
        }

        botonFlotante.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Perfil.this,"Se cerró la sesión", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Perfil.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}