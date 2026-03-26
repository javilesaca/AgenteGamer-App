package com.miapp.agentegamer.ui.worker;

import android.app.Application;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

/**
 * Application class para gestionar workers de background.
 * Agenda el worker del agente financiero al iniciar la app.
 */
public class AgenteGamerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PeriodicWorkRequest agenteWorker = new PeriodicWorkRequest.Builder(
                SistemaFinancieroWorker.class,
                24,
                TimeUnit.HOURS
        ).build();

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "agente_financiero_worker",
                ExistingPeriodicWorkPolicy.KEEP,
                agenteWorker
        );
    }
}
