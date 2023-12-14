package com.example.nossoappe.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nossoappe.R;
import com.example.nossoappe.model.*;

import java.util.List;

public class CadastroGActivity extends AppCompatActivity {

    private EditText nomeEditText;
    private EditText porcentagemEditText;
    private Spinner spinnerContribuintes;
    private Button btnSalvar, btnLimpar;
    private TextView nossoappe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_gastos);

        nomeEditText = findViewById(R.id.nomem);
        porcentagemEditText = findViewById(R.id.porcentagemm);
        spinnerContribuintes = findViewById(R.id.spinnerContribuintes);
        btnSalvar = findViewById(R.id.btnsalvar);
        nossoappe = findViewById(R.id.tituloapp);
        btnLimpar = findViewById(R.id.btnlimpar);

        // Carregue a lista de moradores no Spinner
        carregarMoradoresNoSpinner();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarGasto();
            }
        });
        nossoappe.setOnClickListener(v -> voltarMenu());
        btnLimpar.setOnClickListener(v -> limparCampos());
    }
    private void limparCampos() {
        nomeEditText.setText("");
        porcentagemEditText.setText("");
    }
    private void carregarMoradoresNoSpinner() {
        BancoDAO bancoDAO = new BancoDAO(this);
        List<Morador> moradores = bancoDAO.getListaMoradores();

        // Criando um ArrayAdapter para os nomes dos moradores
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // adicionando no adapter
        for (Morador morador : moradores) {
            adapter.add(morador.getNome());
        }

        spinnerContribuintes.setAdapter(adapter);
    }
    private void salvarGasto() {
        String nome = nomeEditText.getText().toString();
        double valor = Double.parseDouble(porcentagemEditText.getText().toString());

        // Obtém o nome do morador selecionado no Spinner
        String contribuinteNome = spinnerContribuintes.getSelectedItem().toString();

        BancoDAO bancoDAO = new BancoDAO(this);
        long idMorador = bancoDAO.getIdMoradorPorNome(contribuinteNome);

        // PEga se foi pago ou não
        CheckBox checkBoxPago = findViewById(R.id.checkBoxPago);
        boolean pago = checkBoxPago.isChecked();

        Gasto gasto = new Gasto();
        gasto.setNome(nome);
        gasto.setValor(valor);
        gasto.setPago(pago);

        // Salva o gasto com o ID do morador
        gasto.setIdMorador(idMorador);
        bancoDAO.salvarGasto(gasto);

        Toast.makeText(this, "Gasto salvo com sucesso!", Toast.LENGTH_SHORT).show();
        voltarMenu();
    }

    private void voltarMenu() {
        finish();
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}
