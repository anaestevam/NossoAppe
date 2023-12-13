package com.example.nossoappe.adapters;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nossoappe.R;
import com.example.nossoappe.model.BancoDAO;
import com.example.nossoappe.model.Gasto;
import com.example.nossoappe.model.Morador;

import java.util.ArrayList;
import java.util.List;

public class GastoAdapter extends RecyclerView.Adapter<GastoAdapter.MyViewHolder> {

    private List<Gasto> listagastos = new ArrayList<>();
    private final BancoDAO bancoDAO;

    public GastoAdapter(List<Gasto> listagastos, BancoDAO bancoDAO) {
        this.listagastos = listagastos;
        this.bancoDAO = bancoDAO;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Gasto gasto = listagastos.get(position);
        holder.titulo.setText(gasto.getNome());
        holder.valor.setText("R$: " + gasto.getValor());
        long idMoradorPagou = gasto.getIdMorador();
        Morador quemPagou = bancoDAO.getMoradorPorId(idMoradorPagou);
        if (quemPagou != null) {
            holder.pagopor.setText("Contribuinte: " + quemPagou.getNome());
        } else {
            holder.pagopor.setText("Contribuinte: -");
        }
        holder.pago.setText(gasto.isPago() ? "Pago" : "Não Pago");
    }

    @Override
    public int getItemCount() {
        return listagastos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private TextView valor;
        private TextView pagopor;
        private TextView pago;

        private ImageButton apagar;
        private ImageButton pagar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.tvtitulo);
            valor = itemView.findViewById(R.id.tvvalor);
            pagopor = itemView.findViewById(R.id.tvpagopor);
            pago = itemView.findViewById(R.id.tvpago);
            apagar = itemView.findViewById(R.id.bntapagar);
            pagar = itemView.findViewById(R.id.bntpagar);

            apagar.setOnClickListener(v -> apagarGasto(getAdapterPosition()));
            pagar.setOnClickListener(v -> pagarGasto(getAdapterPosition()));

        }

        public void apagarGasto(int position) {
            Gasto gasto = listagastos.get(position);

            bancoDAO.deletarGasto(String.valueOf(gasto.getNome()));
            bancoDAO.close();
            // Remova o item da lista e notifique o RecyclerView
            listagastos.remove(position);
            notifyItemRemoved(position);
        }
        public void pagarGasto(int position) {
            Gasto gasto = listagastos.get(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle("Confirmar Pagamento");
            builder.setMessage("Deseja marcar este gasto como pago?");

            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Atualize a coluna "pago" no banco de dados
                    gasto.setPago(true);
                    bancoDAO.atualizarPagamentoGasto(gasto); // Substitua pelo método correto
                    bancoDAO.close();
                    // Atualize a interface do usuário
                    notifyItemChanged(position);
                }
            });

            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Não faça nada, apenas feche o diálogo
                    dialog.dismiss();
                }
            });

            builder.show();
        }

    }
}




