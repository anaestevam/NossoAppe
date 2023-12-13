package com.example.nossoappe.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.nossoappe.R;
import com.example.nossoappe.adapters.GastoAdapter;
import com.example.nossoappe.model.BancoDAO;
import com.example.nossoappe.model.Gasto;

import java.util.ArrayList;
import java.util.List;

public class ListadeGastos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Gasto> listagastos = new ArrayList<>();
    private BancoDAO bancoDAO;
    private Button voltarmenu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gastos2);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        voltarmenu = findViewById(R.id.voltar2);

        voltarmenu.setOnClickListener(v -> voltarMenu());
        bancoDAO = new BancoDAO(this);
        listagastos = bancoDAO.getListaGastos();

        GastoAdapter adapter = new GastoAdapter(listagastos, bancoDAO);
        recyclerView.setAdapter(adapter);
    }
    private void voltarMenu() {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
        finish();
    }
}
