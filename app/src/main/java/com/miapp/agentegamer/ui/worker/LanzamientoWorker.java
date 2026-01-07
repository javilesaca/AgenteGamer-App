package com.miapp.agentegamer.ui.worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.miapp.agentegamer.data.model.WishlistEntity;
import com.miapp.agentegamer.data.repository.WishlistRepository;
import com.miapp.agentegamer.util.NotificationHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class LanzamientoWorker extends Worker {

    private final WishlistRepository repo;

    public LanzamientoWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
        repo = new WishlistRepository(context);
    }

    @NonNull
    @Override
    public Result doWork() {

        List<WishlistEntity> juegos = repo.getWishlistSync();

        for (WishlistEntity juego: juegos) {
            if(salePronto(juego)) {
                lanzarNotificacion(juego);
            }
        }
        return Result.success();
    }

    private void lanzarNotificacion(WishlistEntity juego) {
        String titulo = "Próximo lanzamiento";
        String texto = juego.getNombre() + " sale pronto";

        NotificationHelper.mostrar(getApplicationContext(), titulo, texto);
    }

    private boolean salePronto(WishlistEntity juego) {
        if (juego.getFechaLanzamiento() == null)
            return false;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            Date fechaLanzamiento = sdf.parse(juego.getFechaLanzamiento());
            Date hoy = new Date();

            long diffMillis = fechaLanzamiento.getTime() - hoy.getTime();
            long dias = TimeUnit.MILLISECONDS.toDays(diffMillis);

            return dias >= 0 && dias <= 7;
        }catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
