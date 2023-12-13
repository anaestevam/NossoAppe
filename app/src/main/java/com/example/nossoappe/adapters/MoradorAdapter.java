package com.example.nossoappe.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nossoappe.controller.ListaMActivity;
import com.example.nossoappe.model.Morador;
import com.example.nossoappe.R;

import java.util.List;

public class MoradorAdapter extends ArrayAdapter<Morador> {

    private List<Morador> moradores;

    public MoradorAdapter(@NonNull Context context, @NonNull List<Morador> moradores) {
        super(context, 0, moradores);
        this.moradores = moradores;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_morador, parent, false);
        }

        Morador moradorAtual = getItem(position);

        TextView nomeMoradorTextView = listItemView.findViewById(R.id.nomeMoradorTextView);
        nomeMoradorTextView.setText("Nome: " + moradorAtual.getNome());

        TextView porcentagemMoradorTextView = listItemView.findViewById(R.id.porcentagemMoradorTextView);
        porcentagemMoradorTextView.setText("Porcentagem: " + moradorAtual.getPorcentagem() + "%");

        // Botão "Apagar Morador"
        ImageButton btnApagarMorador = listItemView.findViewById(R.id.btnApagarMorador);
        btnApagarMorador.setOnClickListener(v -> {
            // Chama o método deletarMorador diretamente na atividade
            ((ListaMActivity) getContext()).deletarMorador(moradorAtual);
        });

        return listItemView;
    }

}
