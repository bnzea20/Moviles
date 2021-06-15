package com.interfaces.pokedex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity {
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar;
    private CheckBox terminos;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN=0;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        progressBar= (ProgressBar) findViewById(R.id.progress);
        TextEmail = (EditText) findViewById(R.id.correo);
        TextPassword = (EditText) findViewById(R.id.clave);
        btnRegistrar = (Button) findViewById(R.id.registrarseBtn);
        terminos = (CheckBox) findViewById(R.id.checkBox2);
        progressDialog=new ProgressDialog(this);
        //Autenticación con Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String email=TextEmail.getText().toString().trim();
                String password=TextPassword.getText().toString().trim();
                registrarUsuario(email,password);
            }
            });
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@Nullable FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
                //Pregunta si ya existe un usuario logueado
                if (user != null) {
                    irSiguiente();
                    Registro.this.finish();
                }
            }

        };


        signInButton=(SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.signInButton:
                        signIn();
                        break;
                }
            }
        });
        //Configuracion de opciones para el inicio de sesión
        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void registrarUsuario(String email, String password){
        //Se obtiene el email y contraseña desde los textbox
        //Se veriffican que no existan campos vacios
        if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){
            Toast.makeText(this,"Se requieren llenar todos los campos", Toast.LENGTH_LONG).show();
            return;
        }
        if(!terminos.isChecked()){
            Toast.makeText(this,"Se requiere aceptar los terminos y condiciones para usar Pokédex", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Realizando registro en Pokédex...");
        progressDialog.show();

        //Crear un nuevo usuario con correo
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Registro.this,"Se ha registrado con su correo exitosamente en la Pokédex", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Registro.this,"No se ha podido registrar con correo"+task.getException(), Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    @Override
    protected void onStart() {
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
        super.onStart();
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
    }

    @Override
    protected void onStop() {
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
        super.onStop();
    }
    //Metodo que inicia sesion con Google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Resultado retornado de lanzar el Intent de GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // No hay necesidad de listener ya que la tarea siempre se completa
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Si el inicio de sesión resulta exitosa con google, procede a hacerlo con Firebase
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // El objeto ApiException nos provee de errores con mas detalles
            Log.w("Error:", "signInResult:failed code=" + e.getStatusCode());
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) {
        progressBar.setVisibility(View.VISIBLE);
        signInButton.setVisibility(View.GONE);
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@Nullable Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        signInButton.setVisibility(View.VISIBLE);
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Autenticación fallida", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Acceso correcto", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void irSiguiente(){
        Intent i = new Intent(Registro.this, MenuPrincipal.class);
        startActivity(i);
    }
    public void irIniciar(View view){
        Intent i = new Intent(Registro.this, InicioSesion.class);
        startActivity(i);
        finish();
    }


}