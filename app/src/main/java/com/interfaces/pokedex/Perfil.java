package com.interfaces.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private TextView Txtcorreo, TxtUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Txtcorreo=(TextView)findViewById(R.id.txtCorreo);
        TxtUID=(TextView)findViewById(R.id.txtUID);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            Txtcorreo.setText("Correo: "+user.getEmail().toString().trim());
            TxtUID.setText("UID: "+user.getUid().toString().trim());
        }

    }
}