package com.miapp.agentegamer.ui.lanzamientos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.data.model.LanzamientoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LanzamientosAdapter extends RecyclerView.Adapter<LanzamientosAdapter.ViewHolder> {

    private List<LanzamientoEntity> lista = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lanzamiento, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int pos) {
        LanzamientoEntity l = lista.get(pos);

        h.nombre.setText(l.getNombre());
        h.precio.setText("Precio estimado: " + l.getPrecioEstimado() + " €");

        long dias = TimeUnit.MILLISECONDS.toDays(
                l.getFechaLanzamiento() - System.currentTimeMillis()
        );

        h.dias.setText("Sale en " + dias + " días");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setLista(List<LanzamientoEntity> nueva) {
        lista = nueva;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio, dias;

        ViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.tvNombre);
            precio = v.findViewById(R.id.tvPrecio);
            dias = v.findViewById(R.id.tvDias);
        }
    }
}