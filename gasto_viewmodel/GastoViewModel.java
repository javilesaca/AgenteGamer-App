package com.miapp.agentegamer.viewmodel;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.miapp.agentegamer.R;
import com.miapp.agentegamer.agent.AgenteFinanciero;
import com.miapp.agentegamer.data.model.GastoEntity;
import com.miapp.agentegamer.data.repository.GastoRepository;
import com.miapp.agentegamer.ui.model.EstadoFinancieroUI;

import java.util.List;

public class GastoViewModel extends AndroidViewModel {

    private final GastoRepository repository;
    private final LiveData<List<GastoEntity>> listaGastos;
    private final MutableLiveData<String> recomendacion = new MutableLiveData<>();
    protected AgenteFinanciero agenteFinanciero;
    private final MutableLiveData<Double> totalGastos = new MutableLiveData<>();
    private MutableLiveData<AgenteFinanciero.EstadoFinanciero> estadoFinanciero =
            new MutableLiveData<>();
    private final MutableLiveData<EstadoFinancieroUI> estadoUI = new MutableLiveData<>();

    public GastoViewModel(@NonNull Application application) {
        super(application);
        repository = new GastoRepository(application);

        listaGastos = repository.obtenerGastos();

        listaGastos.observeForever(gastos -> recalcularEstado(gastos));
    }

    public void setAgenteFinanciero(AgenteFinanciero agente) {
        this.agenteFinanciero = agente;
        recalcularEstado(repository.obtenerGastos().getValue());
    }

    public LiveData<List<GastoEntity>> getListaGastos() {
        return listaGastos;
    }

    public LiveData<Double> getTotalGastos() {
        return totalGastos;
    }

    public LiveData<EstadoFinancieroUI> getEstadoUI() {
        return estadoUI;
    }

    private void recalcularEstado(List<GastoEntity> gastos) {
        if (agenteFinanciero == null || gastos == null) return;

        double total = agenteFinanciero.calcularTotalGastos(gastos);
        double porcentaje = agenteFinanciero.calcularPorcentajeGastado(gastos);

        AgenteFinanciero.EstadoFinanciero estado = agenteFinanciero.obtenerEstado(total);
        String mensaje = agenteFinanciero.generarRecomendacion(gastos);

        int color = switch (estado) {
            case VERDE -> R.color.estado_verde;
            case AMARILLO -> R.color.estado_amarillo;
            case ROJO -> R.color.estado_rojo;
        };

        estadoUI.setValue(new EstadoFinancieroUI(estado, mensaje, color, porcentaje));
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

    public void borrarTodosLosGastos() {
        repository.borrarTodosLosGastos();
    }
}
