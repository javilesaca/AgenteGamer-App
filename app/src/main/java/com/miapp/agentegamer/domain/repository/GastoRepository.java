package com.miapp.agentegamer.domain.repository;

import androidx.lifecycle.LiveData;

import com.miapp.agentegamer.data.local.entity.GastoEntity;

import java.util.List;

/**
 * Interfaz del repositorio de gastos.
 * Define las operaciones para gestionar gastos en videojuegos.
 */
public interface GastoRepository {
    LiveData<List<GastoEntity>> obtenerGastos();
    void insertarGasto(GastoEntity gasto);
    void actualizarGasto(GastoEntity gasto);
    void borrarGasto(GastoEntity gasto);
    void borrarTodosLosGastos();
    LiveData<Double> getGastoMesActual();
    void getTotalGastadoMesSync(OnTotalGastadoCallback callback);

    interface OnTotalGastadoCallback {
        void onSuccess(double total);
    }
}
