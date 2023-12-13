package com.example.nossoappe.controller;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.nossoappe.R;

public class MudarSenhaActivity extends AppCompatActivity {

    private EditText etNovaSenha;
    private Button btnSalvar;
    private Button btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudar_senha);

        etNovaSenha = findViewById(R.id.etNovaSenha);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Para salvar a nova senha e indicar o sucesso
                salvarNovaSenha();
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarLogin();
            }
        });
    }

    private void salvarNovaSenha() {
        String novaSenha = etNovaSenha.getText().toString();

        if (novaSenha.trim().isEmpty()) {
            Toast.makeText(this, "Digite uma nova senha antes de salvar!", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", "admin"); // Nome de usuário fixo como "admin"
            editor.putString("password", novaSenha);
            editor.apply();

            setResult(RESULT_OK);
            Toast.makeText(this, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private void voltarLogin(){
        finish();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
