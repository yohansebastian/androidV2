package com.example.mobiles2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText etEmail, etPassword;
    Button btnLogin,btnTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnTest = findViewById(R.id.btnTest);

        btnTest.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }
    public void createUser(){
        Map<String, Object> userData = new HashMap<>();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        userData.put("email",email);
        userData.put("pass",password);
        db.collection("users")
                .add(userData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Usuario agregado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "No se pudo agregar", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void login(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        Toast.makeText(this, email+password, Toast.LENGTH_SHORT).show();
        if(email.equals("")){
            Toast.makeText(this, "Email Vacío", Toast.LENGTH_SHORT).show();
        }
        else if(password.length() == 0){
            Toast.makeText(this, "Password Vacío", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Login Correcto", Toast.LENGTH_SHORT).show();
        }
    }
    /*
    Se crea una carpeta llamada layout-land en Project-app-src-main-res
    donde se copian los archivos .xml que son los de diseño, para adoptar
    la vista horizontal.

    Que tenga compatibilidad con distintos tipos de pantalla.

    android soporte multiples tamaños de pantalla, buscar eso para
     que la aplicación sea multipantalla
     https://material.io/design/material-theming/overview.html#using-material-theming
     https://material.io/resources/color/#!/?view.left=1&view.right=0&primary.color=4DD0E1&secondary.color=F8BBD0
     para ver las combinaciones de colores usar la siguiente pagina :
     https://www.materialpalette.com/lime/yellow
     https://www.materialpalette.com/icons
    */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnTest:
                createUser();
                break;
        }
    }
}