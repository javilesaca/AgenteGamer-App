package com.miapp.agentegamer.data.repository;

import android.content.Context;

import androidx.room.Room;

import com.miapp.agentegamer.data.database.AppDatabase;
import com.miapp.agentegamer.data.model.GastoEntity;

import java.util.List;

public class GastoRepository {

    private AppDatabase db;

    public GastoRepository(Context context) {
        db = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "agente_gamer_db"
        ).allowMainThreadQueries().build();
        // Nota: allowMainThreadQueries es solo para desarrollo.
        // Más adelante lo quitare y hare las operaciones en hilos.
    }

    // INSERTAR
    public void insertarGasto(GastoEntity gasto) {
        db.gastoDao().insertGasto(gasto);
    }

    // ACTUALIZAR
    public void actualizarGasto(GastoEntity gasto) {
        db.gastoDao().updateGasto(gasto);
    }

    // ELIMINAR
    public void borrarGasto(GastoEntity gasto) {
        db.gastoDao().deleteGasto(gasto);
    }

    // OBTENER TODOS
    public List<GastoEntity> obtenerGastos() {
        return db.gastoDao().getAllGastos();
    }
}
