package com.miapp.agentegamer.ui.wishlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.data.model.WishlistEntity;

import java.util.ArrayList;
import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(WishlistEntity juego);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    private List<WishlistEntity> lista = new ArrayList<>();

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wishlist, parent, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        WishlistEntity juego = lista.get(position);

        holder.nombre.setText(juego.getNombre());
        holder.precio.setText("Precio estimado: " + juego.getPrecioEstimado() + " €");
        holder.recomendacion.setText("Pendiente de evaluación");

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(juego);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setLista(List<WishlistEntity> nuevalista) {
        lista = nuevalista;
        notifyDataSetChanged();
    }

    static class WishlistViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, precio, recomendacion;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombreJuego);
            precio = itemView.findViewById(R.id.tvPrecio);
            recomendacion = itemView.findViewById(R.id.tvRecomendacion);

        }
    }

 }
