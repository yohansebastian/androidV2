package com.example.mobiles2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiles2.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding mainBinding;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = mainBinding.getRoot();

        setContentView(view);

        realtimeData();

        mainBinding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
        mainBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    public void createUser(){
        Map<String, Object> userData = new HashMap<>();
        String email =  mainBinding.etEmail.getText().toString();
        String password =  mainBinding.etPassword.getText().toString();
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
        String email =  mainBinding.etEmail.getText().toString();
        String password =  mainBinding.etPassword.getText().toString();
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

     02/10/2021

     View Bindig
    */

    public void realtimeData(){
        /*final DocumentReference docRef = db.collection("users")
                .document("471iMwyyybpvn4o6Gkqq");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value,
                                @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w("Error data", "Listen failed.", error);
                    return;
                }

                if (value != null && value.exists()) {

                    Log.d("Snapshop", "Current data: " + value.getData());
                } else {
                    Log.d("Snapshop", "Current data: null");
                }
            }
        });*/
        final DocumentReference docRef = db.collection("users").document("upxsgqcP7QL8e7fk30oL");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("Error Data", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d("Snapshop", "Current data: " + snapshot.getData());
                } else {
                    Log.d("Snapshop", "Current data: null");
                }
            }
        });
    }
}