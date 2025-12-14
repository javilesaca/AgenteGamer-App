package com.miapp.agentegamer.ui.gastos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.data.model.GastoEntity;

import java.util.ArrayList;
import java.util.List;

public class GastoAdapter extends RecyclerView.Adapter<GastoAdapter.GastoViewHolder> {

    private List<GastoEntity> lista;

    public GastoAdapter(List<GastoEntity> listaInicial){
        this.lista = listaInicial;
    }

    @NonNull
    @Override
    public GastoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gasto, parent, false);
        return new GastoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull GastoViewHolder holder, int position) {
        GastoEntity g = lista.get(position);
        holder.textConcepto.setText(g.getNombreJuego());
        holder.textCantidad.setText(String.valueOf(g.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setLista(List<GastoEntity> nuevaLista) {
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

