package com.miapp.agentegamer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.miapp.agentegamer.agent.AgenteFinanciero;
import com.miapp.agentegamer.data.model.GastoEntity;
import com.miapp.agentegamer.data.model.LanzamientoEntity;
import com.miapp.agentegamer.data.model.WishlistEntity;
import com.miapp.agentegamer.data.repository.LanzamientoRepository;
import com.miapp.agentegamer.data.repository.WishlistRepository;
import com.miapp.agentegamer.util.FechaUtils;

import java.util.Date;
import java.util.List;

public class WishlistViewModel extends AndroidViewModel {

    private final WishlistRepository repo;
    private final AgenteFinanciero agente;
    private final LanzamientoRepository repoLanzamiento;

    public WishlistViewModel(@NonNull Application app) {
        super(app);
        repo = new WishlistRepository(app.getApplicationContext());
        repoLanzamiento = new LanzamientoRepository(app);
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
        boolean puede = agente.puedeComprar(gastoActual, precioJuego);
        return puede
                ? "Compra viable con tu presupuesto"
                : "No recomendable, supera tu presupuesto";
    }

    public void comprarJuego(WishlistEntity juego, double precioFinal) {

        Date hoy = new Date();
        Date fechaLanzamiento = FechaUtils.parseFecha(juego.getFechaLanzamiento());

        //Si es un lanzamiento se guarda en lanzamientos
        if (fechaLanzamiento != null && fechaLanzamiento.after(hoy)) {

            LanzamientoEntity lanzamiento = new LanzamientoEntity(
                    juego.getGameId(),
                    juego.getNombre(),
                    fechaLanzamiento.getTime(),
                    precioFinal
            );
            repoLanzamiento.insertar(lanzamiento);
        } else {

            //Crear gasto desde el juego
            GastoEntity gasto = new GastoEntity(
                    juego.getNombre(),
                    precioFinal,
                    FechaUtils.ahoraTimestamp()
            );
            //Insertar en Gastos
            repo.insertarGasto(gasto);
            //Eliminar de whishlist
            repo.borrar(juego);
        }
    }
}