package com.example.nossoappe.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nossoappe.R;
import com.example.nossoappe.model.BancoDAO;
import com.example.nossoappe.model.Gasto;
import com.example.nossoappe.model.Morador;

import java.util.List;

public class EditarGastoActivity extends AppCompatActivity {

    private EditText nome;
    private EditText valor;
    private Spinner spinnerContribuintes;
    private TextView voltarmenu;
    private Button btnSalvar, btnDeletar, btnLimpar;
    private BancoDAO banco;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_gastos);

        nome = findViewById(R.id.nomem);
        valor = findViewById(R.id.porcentagemm);
        btnSalvar = findViewById(R.id.btnsalvar);
        btnDeletar = findViewById(R.id.deletargasto);
        voltarmenu = findViewById(R.id.tituloapp);
        spinnerContribuintes = findViewById(R.id.spinnerContribuintes);
        btnLimpar = findViewById(R.id.btnlimpar);

        btnDeletar.setOnClickListener(v -> deletarGasto());
        voltarmenu.setOnClickListener(v -> voltarMenu());
        btnLimpar.setOnClickListener(v -> limparCampos());
        carregarMoradoresNoSpinner();

        banco = new BancoDAO(this);

        btnSalvar.setOnClickListener(v -> alterarGasto());
    }

    private void carregarMoradoresNoSpinner() {
        BancoDAO bancoDAO = new BancoDAO(this);
        List<Morador> moradores = bancoDAO.getListaMoradores();

        // Crie um ArrayAdapter para os nomes dos moradores
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Adicione os nomes dos moradores ao ArrayAdapter
        for (Morador morador : moradores) {
            adapter.add(morador.getNome());
        }

        // Defina o adapter no Spinner
        spinnerContribuintes.setAdapter(adapter);
    }
    private void alterarGasto() {

        String novoNomeGasto = nome.getText().toString();
        String valorGasto = valor.getText().toString();

        if (novoNomeGasto.isEmpty() || valorGasto.isEmpty()) {
            Toast.makeText(this, "Existem campos em branco!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!banco.gastoExiste(novoNomeGasto)) {
            Toast.makeText(this, "Esta descrição não foi cadastrada!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Gasto GastoAtualizado = new Gasto();
            GastoAtualizado.setNome(novoNomeGasto);
            GastoAtualizado.setValor(Double.parseDouble(valorGasto));

            banco.atualizarGasto(GastoAtualizado);

            Toast.makeText(this, "Gasto Alterado com sucesso!", Toast.LENGTH_SHORT).show();
            banco.close();

            voltarMenu();
        }
    }
    private void limparCampos() {
        nome.setText("");
        valor.setText("");
    }
    private void deletarGasto() {

        String nomeGasto = nome.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tem certeza de que deseja excluir este gasto?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    if (!banco.gastoExiste(nomeGasto)) {
                        Toast.makeText(this, "Descrição do gasto não existe!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        banco.deletarGasto(nomeGasto);

                        Toast.makeText(this, "Gasto deletado com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    banco.close();
                    voltarMenu();
                })
                .setNegativeButton("Não", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void voltarMenu() {
        finish();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}