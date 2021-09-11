package com.example.mobiles2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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


    */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnTest:
                Toast.makeText(this, "Es el test", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}