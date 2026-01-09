package com.miapp.agentegamer.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.miapp.agentegamer.data.dao.LanzamientoDao;
import com.miapp.agentegamer.data.database.AppDatabase;
import com.miapp.agentegamer.data.model.LanzamientoEntity;

import java.util.List;
import java.util.concurrent.Executors;

public class LanzamientoRepository {

    private final LanzamientoDao dao;

    public LanzamientoRepository(Application app) {
        AppDatabase db = AppDatabase.getInstance(app);
        dao = db.lanzamientoDao();
    }

    public void insertar(LanzamientoEntity lanzamiento) {
        Executors.newSingleThreadExecutor().execute(()-> dao.insertar(lanzamiento));
    }

    public LiveData<List<LanzamientoEntity>> getProximosLanzamientos() {
        return dao.getProximosLanzamientos();
    }
}
