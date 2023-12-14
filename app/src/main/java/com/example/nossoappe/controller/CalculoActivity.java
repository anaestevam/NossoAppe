package com.example.nossoappe.controller;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.nossoappe.R;
import com.example.nossoappe.model.BancoDAO;

public class CalculoActivity extends AppCompatActivity {

    private TextView totais;
    private TextView gastosmorador;
    private TextView pendenciasmorador;
    private Button voltar;
    private  double totalgastos=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        totais = findViewById(R.id.tvtotal);
        gastosmorador = findViewById(R.id.tvgastosmorador);
        pendenciasmorador = findViewById(R.id.tvpendencias);
        voltar = findViewById(R.id.voltarparamenu);

        voltar.setOnClickListener(v -> voltarMenu());
        this.calculaTotal();
        this.calculaGastosmorador();
        this.calculaPendencias();
    }

    public void calculaTotal() {
        BancoDAO bancoDAO = new BancoDAO(this); // Substitua "this" pelo contexto apropriado
        totalgastos = bancoDAO.calcularTotalGastosNaoPagos();
        totais.setText("R$: " + totalgastos);
        bancoDAO.close();
    }
    public void calculaGastosmorador() {
        BancoDAO bancoDAO = new BancoDAO(this);
        String exibir = bancoDAO.obterStringGastosMorador();
        gastosmorador.setText(exibir);
        bancoDAO.close();
    }

    public void calculaPendencias() {
        BancoDAO bancoDAO = new BancoDAO(this);
        String exibirp = bancoDAO.obterStringPendencias();
        pendenciasmorador.setText(exibirp);
        bancoDAO.close();
    }
    private void voltarMenu() {
        finish();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}