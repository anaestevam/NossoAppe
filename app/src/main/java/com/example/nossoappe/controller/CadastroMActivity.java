package com.example.nossoappe.controller;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nossoappe.model.BancoDAO;
import com.example.nossoappe.model.Morador;
import com.example.nossoappe.R;
public class CadastroMActivity extends AppCompatActivity {
    private EditText nome, porcentagem;
    private Button btnSalvar, btnLimpar;
    private TextView nossoappe;
    private BancoDAO banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_morador);

        nome = findViewById(R.id.nomem);
        porcentagem = findViewById(R.id.porcentagemm);

        btnSalvar = findViewById(R.id.btnsalvar);
        btnLimpar = findViewById(R.id.btnlimpar);
        nossoappe = findViewById(R.id.tituloapp);

        banco = new BancoDAO(this); // Inicializa o objeto BancoDAO

        btnLimpar.setOnClickListener(v -> limparCampos());
        btnSalvar.setOnClickListener(v -> salvarMorador());
        nossoappe.setOnClickListener(v -> voltarMenu());
    }

    private void limparCampos() {
        nome.setText("");
        porcentagem.setText("");
    }

    private void salvarMorador() {
        String nomeMorador = nome.getText().toString();
        String porcentagemMorador = porcentagem.getText().toString();

        if (nomeMorador.isEmpty() || porcentagemMorador.isEmpty()) {
            Toast.makeText(this, "Existem campos em branco!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verifica se o nome do morador já existe no banco
        if (banco.moradorExiste(nomeMorador)) {
            Toast.makeText(this, "Este nome de morador já existe!", Toast.LENGTH_SHORT).show();
            return;
        }
        BancoDAO banco = new BancoDAO(this);

        try {
            Morador morador = new Morador();
            morador.setNome(nomeMorador);
            morador.setPorcentagem(Double.parseDouble(porcentagemMorador));

            banco.salvarMorador(morador);
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
        } finally {
            banco.close();
        }

    }

    private void voltarMenu() {
        finish();
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}
