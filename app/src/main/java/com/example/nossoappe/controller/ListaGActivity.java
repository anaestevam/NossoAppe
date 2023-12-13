package com.example.nossoappe.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.nossoappe.R;
import com.example.nossoappe.model.BancoDAO;
import com.example.nossoappe.model.Gasto;

import java.util.List;

public class ListaGActivity extends AppCompatActivity {

    private BancoDAO banco;
    private ListView listViewGastos;
    private Button voltarmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gastos);

        banco = new BancoDAO(this);
        listViewGastos = findViewById(R.id.listav); // Certifique-se de ter um ListView no seu layout
        voltarmenu = findViewById(R.id.voltarmenu);

        voltarmenu.setOnClickListener(v -> voltarMenu());

        BancoDAO bancoDAO = new BancoDAO(this);

        List<Gasto> listaDeGastos = bancoDAO.getListaGastos();

        GastoAdapter gastoAdapter = new GastoAdapter(this, listaDeGastos, bancoDAO);

        ListView listViewGastos = findViewById(R.id.listav);
        listViewGastos.setAdapter(gastoAdapter);
    }

    private void voltarMenu() {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
        finish();
    }
}

