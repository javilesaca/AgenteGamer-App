package com.miapp.agentegamer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.miapp.agentegamer.data.remote.model.GameDto;
import com.miapp.agentegamer.data.remote.repository.GamesRepository;

import java.util.List;

public class GamesViewModel extends ViewModel {

    private final GamesRepository repository;
    private LiveData<List<GameDto>> juegos;
    private final MutableLiveData<Boolean> cargando = new MutableLiveData<>();
    public GamesViewModel() {
        repository = new GamesRepository();
    }

    public LiveData<List<GameDto>> obtenerJuegos(String apiKey) {
        if (juegos == null) {
            juegos = repository.getGames(apiKey);
        }
        return juegos;
    }

    /*public LiveData<List<GameDto>> buscarJuegos(String apiKey, String texto) {
        return repository.searchGames(apiKey, texto);
    }*/

    public LiveData<List<GameDto>> buscarJuegosPaginados(String apiKey, String query, boolean reset) {
        cargando.setValue(true);
        return repository.buscarJuegosPaginados(apiKey, query, reset, cargando);
    }

    public LiveData<Boolean> isCargando() {
        return cargando;
    }
}
