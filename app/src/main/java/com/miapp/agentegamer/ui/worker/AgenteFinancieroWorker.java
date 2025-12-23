package com.miapp.agentegamer.ui.worker;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.miapp.agentegamer.agent.AgenteFinanciero;
import com.miapp.agentegamer.data.model.WishlistEntity;
import com.miapp.agentegamer.data.repository.WishlistRepository;
import com.miapp.agentegamer.util.NotificationHelper;

import java.util.List;

public class AgenteFinancieroWorker extends Worker {

    public AgenteFinancieroWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {

        //Obtener Application desde el Context del Worker
        Application app = (Application) getApplicationContext();
        //Repository (Room)
        WishlistRepository repo = new WishlistRepository(app);
        //Lógica del Agente
        AgenteFinanciero agente = new AgenteFinanciero(100);
        //Obtener la wishlist (sin LiveData, acceso directo)
        List<WishlistEntity> wishlist = repo.getWishlistSync();

        double total = repo.getTotalGastado();

        AgenteFinanciero.EstadoFinanciero estado = agente.obtenerEstado(total);

        switch (estado) {
            case VERDE:
                NotificationHelper.mostrar(
                        getApplicationContext(),
                        "Presupuesto saludable",
                        "Puedes permitirte nuevas compras"
                );
                break;

            case AMARILLO:
                NotificationHelper.mostrar(
                        getApplicationContext(),
                        "Atención",
                        "Estás cerca del límite del presupuesto mensual"
                );
                break;

            case ROJO:
                NotificationHelper.mostrar(
                        getApplicationContext(),
                        "Alerta financiera",
                        "Has superado tu presupuesto mensual"
                );
                break;
        }

        return Result.success();
    }
}
