package com.miapp.agentegamer.ui.gastos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.data.model.Gasto;

import java.util.ArrayList;
import java.util.List;

public class GastoAdapter extends RecyclerView.Adapter<GastoAdapter.GastoViewHolder> {

    private List<Gasto> lista = new ArrayList<>();

    @NonNull
    @Override
    public GastoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gasto, parent, false);
        return new GastoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull GastoViewHolder holder, int position) {
        Gasto g = lista.get(position);
        holder.textConcepto.setText(g.getConcepto());
        holder.textCantidad.setText(String.valueOf(g.getCantidad()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setLista(List<Gasto> nuevaLista) {
        this.lista = nuevaLista;
        notifyDataSetChanged();
    }

    static class GastoViewHolder extends RecyclerView.ViewHolder {

        TextView textConcepto, textCantidad;

        public GastoViewHolder(@NonNull View itemView) {
            super(itemView);
            textConcepto = itemView.findViewById(R.id.textConcepto);
            textCantidad = itemView.findViewById(R.id.textCantidad);
        }
    }
}

