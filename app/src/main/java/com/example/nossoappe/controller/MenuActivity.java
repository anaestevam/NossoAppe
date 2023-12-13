package com.example.nossoappe.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.nossoappe.R;

public class MenuActivity extends AppCompatActivity {

    private Button btnCadastrarM, btnEditarM, btnEditarG;
    private Button btnListarM;
    private Button btnListarG;
    private Button btnCadastrarG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnCadastrarM = findViewById(R.id.btnCadastrarM);
        btnListarM = findViewById(R.id.btnListarM);
        btnEditarM = findViewById(R.id.btnEditarM);
        btnListarG = findViewById(R.id.btnListarG);
        btnCadastrarG = findViewById(R.id.btnCadastrarG);
        btnEditarG = findViewById(R.id.btnEditarG);

        btnCadastrarM.setOnClickListener(v -> abreCadastroM());
        btnEditarM.setOnClickListener(v -> abreEditarM());
        btnListarM.setOnClickListener(v -> abreListaM());
        btnCadastrarG.setOnClickListener(v -> abreCadastroG());
        btnEditarG.setOnClickListener(v -> abreEditarG());
        btnListarG.setOnClickListener(v -> abreListaG());
    }
    private void abreCadastroM() {
        finish();
        Intent i = new Intent(this, CadastroMActivity.class);
        startActivity(i);
    }
    private void abreCadastroG() {
        finish();
        Intent i = new Intent(this, CadastroGActivity.class);
        startActivity(i);
    }

    private void abreListaM() {
        finish();
        Intent i = new Intent(this, ListaMActivity.class);
        startActivity(i);
    }
    private void abreListaG(){
        finish();
       // Intent i = new Intent(this, ListaGActivity.class);
        Intent i = new Intent(this, ListadeGastos.class);
        startActivity(i);
    }
    private void abreEditarM() {
        finish();
        Intent i = new Intent(this, EditarMoradorActivity.class);
        startActivity(i);
    }
    private void abreEditarG() {
        finish();
        Intent i = new Intent(this, EditarGastoActivity.class);
        startActivity(i);
    }
}