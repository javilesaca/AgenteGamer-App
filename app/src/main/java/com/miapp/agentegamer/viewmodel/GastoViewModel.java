package com.miapp.agentegamer.viewmodel;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.Transformations;

import com.miapp.agentegamer.agent.AgenteFinanciero;
import com.miapp.agentegamer.data.model.GastoEntity;
import com.miapp.agentegamer.data.repository.GastoRepository;

import java.util.List;

public class GastoViewModel extends AndroidViewModel {

    private final GastoRepository repository;
    private final LiveData<List<GastoEntity>> listaGastos;

    private AgenteFinanciero agenteFinanciero = new AgenteFinanciero(250);

    public GastoViewModel(@NonNull Application application) {
        super(application);
        repository = new GastoRepository(application);
        listaGastos = repository.obtenerGastos();
    }

    public LiveData<List<GastoEntity>> getListaGastos() {
        return listaGastos;
    }

    public void insertar(GastoEntity gasto) {
        repository.insertarGasto(gasto);
    }

    public void actualizar(GastoEntity gasto) {
        repository.actualizarGasto(gasto);
    }

    public void borrar(GastoEntity gasto) {
        repository.borrarGasto(gasto);
    }

    public double getTotalGastado(List<GastoEntity>gastos) {
        AgenteFinanciero agente = new AgenteFinanciero(250);//presupesto temporal de testeo
        return agente.calcularTotalGastos(gastos);
    }

    public LiveData<String> getRecomendacion() {
        return Transformations.map(listaGastos, gastos ->
                agenteFinanciero.generarRecomendacion(gastos)
        );
    }

}

