package com.miapp.agentegamer.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.miapp.agentegamer.data.dao.GastoDao;
import com.miapp.agentegamer.data.database.AppDatabase;
import com.miapp.agentegamer.data.model.GastoEntity;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GastoRepository {
    private final GastoDao gastoDao;
    private final ExecutorService executorService;

    public GastoRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        gastoDao = db.gastoDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<GastoEntity>> obtenerGastos() {
        return gastoDao.getAllGastos();
    }

    public void insertarGasto(GastoEntity gasto) {
        executorService.execute(() -> gastoDao.insertGasto(gasto));
    }

    public void borrarTodosLosGastos() {
        executorService.execute(() -> gastoDao.deleteAll());
    }
}
