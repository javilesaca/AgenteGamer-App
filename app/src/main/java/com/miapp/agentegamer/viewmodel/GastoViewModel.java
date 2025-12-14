package com.miapp.agentegamer.viewmodel;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import android.app.Application;
import androidx.annotation.NonNull;

import com.miapp.agentegamer.data.model.GastoEntity;
import com.miapp.agentegamer.data.repository.GastoRepository;

import java.util.List;

public class GastoViewModel extends AndroidViewModel {

    private final GastoRepository repository;
    private final LiveData<List<GastoEntity>> listaGastos;

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
}

