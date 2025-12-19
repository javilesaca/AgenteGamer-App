package com.miapp.agentegamer.ui.games;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.data.remote.model.GameDto;

import java.util.ArrayList;
import java.util.List;

public class JuegosAdapter extends RecyclerView.Adapter<JuegosAdapter.JuegoViewHolder> {

    private List<GameDto> lista = new ArrayList<>();

    @NonNull
    @Override
    public JuegoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_juego, parent, false);
        return new JuegoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull JuegoViewHolder holder, int position) {
        GameDto juego = lista.get(position);
        holder.nombre.setText(juego.getName());
        holder.rating.setText("⭐ " + juego.getRating());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setLista(List<GameDto> nuevaLista) {
        lista = nuevaLista;
        notifyDataSetChanged();
    }

    static class JuegoViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, rating;

        public  JuegoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombreJuego);
            rating = itemView.findViewById(R.id.tvRating);
        }
    }
}
