package com.miapp.agentegamer.viewmodel;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import android.app.Application;

import com.miapp.agentegamer.data.model.Gasto;
import com.miapp.agentegamer.data.repository.GastoRepository;

import java.util.List;

public class GastoViewModel extends AndroidViewModel {

    private final GastoRepository repository;
    private final LiveData<List<Gasto>> listaGastos;

    public GastoViewModel(Application application) {
        super(application);
        repository = new GastoRepository(application);
        listaGastos = repository.obtenerGastos();
    }

    public LiveData<List<Gasto>> getListaGastos() {
        return listaGastos;
    }

    public void insertar(Gasto gasto) {
        repository.insertarGasto(gasto);
    }

    public void borrar(Gasto gasto) {
        repository.borrarGasto(gasto);
    }

    public void actualizar(Gasto gasto) {
        repository.actualizarGasto(gasto);
    }
}
