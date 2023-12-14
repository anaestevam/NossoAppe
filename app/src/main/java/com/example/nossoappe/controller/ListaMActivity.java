package com.example.nossoappe.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nossoappe.R;
import com.example.nossoappe.adapters.MoradorAdapter;
import com.example.nossoappe.model.BancoDAO;
import com.example.nossoappe.model.Morador;

import java.util.List;

public class ListaMActivity extends AppCompatActivity{

    private BancoDAO banco;
    private ListView listViewMoradores;
    private Button voltarmenu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_morador);

        banco = new BancoDAO(this);
        listViewMoradores = findViewById(R.id.listav);
        voltarmenu = findViewById(R.id.voltarmenu5);

        voltarmenu.setOnClickListener(v -> voltarMenu());

        List<Morador> moradores = banco.getListaMoradores();

        MoradorAdapter moradorAdapter = new MoradorAdapter(this, moradores);

        listViewMoradores.setAdapter(moradorAdapter);
    }
    public void deletarMorador(Morador morador) {
        String nomeMorador = morador.getNome();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tem certeza de que deseja excluir este morador?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    if (!banco.moradorExiste(nomeMorador)) {
                        Toast.makeText(this, "Este nome de morador não existe!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        banco.deletarMorador(nomeMorador);
                        Toast.makeText(this, "Morador deletado com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    banco.close();
                    // Atualize a lista após excluir o morador
                    carregarListaMoradores();
                })
                .setNegativeButton("Não", (dialog, which) -> dialog.dismiss())
                .show();
    }
    private void carregarListaMoradores() {
        List<Morador> moradores = banco.getListaMoradores();
        MoradorAdapter moradorAdapter = new MoradorAdapter(this, moradores);
        listViewMoradores.setAdapter(moradorAdapter);
    }
    private void voltarMenu() {
        finish();
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}
