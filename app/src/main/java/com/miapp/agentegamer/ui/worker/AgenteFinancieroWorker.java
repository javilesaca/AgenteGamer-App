package com.miapp.agentegamer.ui.worker;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.miapp.agentegamer.agent.AgenteFinanciero;
import com.miapp.agentegamer.data.model.WishlistEntity;
import com.miapp.agentegamer.data.repository.GastoRepository;
import com.miapp.agentegamer.data.repository.WishlistRepository;
import com.miapp.agentegamer.util.FechaUtils;
import com.miapp.agentegamer.util.NotificationHelper;

import java.util.List;

public class AgenteFinancieroWorker extends Worker {

    private final WishlistRepository wishlistRepo;
    private final GastoRepository gastoRepo;
    private final AgenteFinanciero agente;

    public AgenteFinancieroWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params
    ) {
        super(context, params);

        Application app = (Application) getApplicationContext();
        wishlistRepo = new WishlistRepository(app);
        gastoRepo = new GastoRepository(app);
        agente = new AgenteFinanciero(100);
    }

    @NonNull
    @Override
    public Result doWork() {

        //Total gastado por el usuario
        double totalGastado = gastoRepo.getTotalGastado();

        //Juegos en wishlist (acceso síncronico)
        List<WishlistEntity> whishlist = wishlistRepo.getWishlistSync();

        //Estado financiero general
        AgenteFinanciero.EstadoFinanciero estado = agente.obtenerEstado(totalGastado);

        //Notificación general
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

        //Recomendación de no comprar juego
        for (WishlistEntity juego: whishlist) {

            boolean puedeComprar = agente.puedeComprar(totalGastado, juego.getPrecioEstimado());

            if(!puedeComprar) {
                lanzarNotificacionNoComprar(juego);
                break;
            }
        }
        return Result.success();
    }

    // ===============================
    // LANZAMIENTOS PRÓXIMOS
    // ===============================
    private void avisarLanzamientosProximos() {

        List<WishlistEntity> wishlist = wishlistRepo.getWishlistSync();

        for (WishlistEntity juego : wishlist) {

            if (juego.getFechaLanzamiento() == null) continue;

            long dias = FechaUtils.diasHasta(juego.getFechaLanzamiento());

            if (dias > 0 && dias <= 7) {
                NotificationHelper.mostrar(
                        getApplicationContext(),
                        "Próximo lanzamiento",
                        juego.getNombre() + " sale en " + dias + " días"
                );
            }
        }
    }

    // ===============================
    // NOTIFICACION NO COMPRAR
    // ===============================
    private void lanzarNotificacionNoComprar(WishlistEntity juego) {

        NotificationHelper.mostrar(
                getApplicationContext(),
                "No es buen momento para comprar ",
                juego.getNombre() + " (" + juego.getPrecioEstimado() +
                        " €), supera tu presupuesto actual"
        );
    }
}

