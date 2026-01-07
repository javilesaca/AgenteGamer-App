package com.miapp.agentegamer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.miapp.agentegamer.agent.AgenteFinanciero;
import com.miapp.agentegamer.data.model.GastoEntity;
import com.miapp.agentegamer.data.model.WishlistEntity;
import com.miapp.agentegamer.data.repository.WishlistRepository;

import java.util.List;

public class WishlistViewModel extends AndroidViewModel {

    private final WishlistRepository repo;
    private final AgenteFinanciero agente;

    public WishlistViewModel(@NonNull Application app) {
        super(app);
        repo = new WishlistRepository(app.getApplicationContext());
        agente = new AgenteFinanciero(100);
    }

    public void insertar(WishlistEntity juego) {
        repo.insertar(juego);
    }

    public LiveData<List<WishlistEntity>> getWishlist() {
        return repo.getWishlist();
    }

    public void actualizar(WishlistEntity juego) {
        repo.actualizar(juego);
    }

    public String obtenerRecomendacion(double gastoActual, double precioJuego) {
        boolean puede = agente.puedeComprar( gastoActual, precioJuego);
        return puede
                ? "Compra viable con tu presupuesto"
                : "No recomendable, supera tu presupuesto";
    }

    public void comprarJuego(WishlistEntity juego, double precioFinal) {
        //Crear gasto desde el juego
        GastoEntity gasto = new GastoEntity(
                juego.getNombre(),
                precioFinal,
                System.currentTimeMillis()
        );
        //Insertar en Gastos
        repo.insertarGasto(gasto);
        //Eliminar de whishlist
        repo.borrar(juego);
    }
}
