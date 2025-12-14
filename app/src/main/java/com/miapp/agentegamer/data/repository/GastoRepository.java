package com.miapp.agentegamer.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.miapp.agentegamer.data.dao.GastoDao;
import com.miapp.agentegamer.data.database.AppDatabase;
import com.miapp.agentegamer.data.model.GastoEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GastoRepository {

    private final GastoDao gastoDao;
    private final LiveData<List<GastoEntity>> listaGastos;
    private final ExecutorService executorService;

    public GastoRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        gastoDao = db.gastoDao();
        listaGastos = gastoDao.getAllGastos();

        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<GastoEntity>> obtenerGastos() {
        return listaGastos;
    }

    public void insertarGasto(GastoEntity gasto) {
        executorService.execute(() -> gastoDao.insertGasto(gasto));
    }

    public void actualizarGasto(GastoEntity gasto) {
        executorService.execute(() -> gastoDao.updateGasto(gasto));
    }

    public void borrarGasto(GastoEntity gasto) {
        executorService.execute(() -> gastoDao.deleteGasto(gasto));
    }
}
