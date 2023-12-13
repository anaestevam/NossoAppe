package com.example.nossoappe.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nossoappe.R;
import com.example.nossoappe.model.BancoDAO;
import com.example.nossoappe.model.Morador;

public class EditarMoradorActivity extends AppCompatActivity {

    private EditText nome, porcentagem;

    private TextView voltarmenu;
    private Button btnSalvar, btnDeletar;;

    private BancoDAO banco;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_morador);

        nome = findViewById(R.id.nomem);
        porcentagem = findViewById(R.id.porcentagemm);
        btnSalvar = findViewById(R.id.btnsalvar);
        btnDeletar = findViewById(R.id.deletarmorador);
        voltarmenu = findViewById(R.id.tituloapp);

        btnDeletar.setOnClickListener(v -> deletarMorador());
        voltarmenu.setOnClickListener(v -> VoltarMenu());

        banco = new BancoDAO(this);

        btnSalvar.setOnClickListener(v -> alterarMorador());
    }
    private void alterarMorador() {

        String novoNomeMorador = nome.getText().toString();
        String porcentagemMorador = porcentagem.getText().toString();

        if (novoNomeMorador.isEmpty() || porcentagemMorador.isEmpty()) {
            Toast.makeText(this, "Existem campos em branco!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Adiciona verificação da porcentagem aqui
        double porcentagemValue;
        try {
            porcentagemValue = Double.parseDouble(porcentagemMorador);
            if (porcentagemValue < 0 || porcentagemValue > 100) {
                Toast.makeText(this, "Porcentagem deve estar entre 0 e 100!", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Porcentagem inválida!", Toast.LENGTH_SHORT).show();
            return;
        }
        // Verifica se o novo nome é diferente do nome atual
        if (!banco.moradorExiste(novoNomeMorador)) {
            Toast.makeText(this, "Este nome de morador não existe!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            // Atualiza os detalhes do morador no banco de dados
            Morador moradorAtualizado = new Morador();
            moradorAtualizado.setNome(novoNomeMorador); // Não deve ser necessário alterar o nome
            moradorAtualizado.setPorcentagem(Double.parseDouble(porcentagemMorador));

            banco.atualizarMorador(moradorAtualizado);

            Toast.makeText(this, "Morador Alterado com sucesso!", Toast.LENGTH_SHORT).show();
            banco.close();

            VoltarMenu();
        }
    }
    private void deletarMorador() {

        String nomeMorador = nome.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tem certeza de que deseja excluir este morador?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    // Deleta o morador do banco de dados
                    if (!banco.moradorExiste(nomeMorador)) {
                        Toast.makeText(this, "Este nome de morador não existe!", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        banco.deletarMorador(nomeMorador);

                        Toast.makeText(this, "Morador deletado com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    banco.close();
                    VoltarMenu();
                })
                .setNegativeButton("Não", (dialog, which) -> dialog.dismiss())
                .show();
    }


    private void VoltarMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

}