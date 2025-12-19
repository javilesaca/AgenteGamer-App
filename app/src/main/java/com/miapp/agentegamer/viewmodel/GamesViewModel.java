package com.miapp.agentegamer.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.miapp.agentegamer.data.remote.model.GameDto;
import com.miapp.agentegamer.data.remote.repository.GamesRepository;

import java.util.List;

public class GamesViewModel extends ViewModel {

    private final GamesRepository repository;
    private LiveData<List<GameDto>> juegos;

    public GamesViewModel() {
        repository = new GamesRepository();
    }

    public LiveData<List<GameDto>> obtenerJuegos(String apiKey) {
        if (juegos == null) {
            juegos = repository.getGames(apiKey);
        }
        return juegos;
    }
}
