package com.miapp.agentegamer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.miapp.agentegamer.data.model.LanzamientoEntity;
import com.miapp.agentegamer.data.repository.LanzamientoRepository;

import java.util.List;

public class LanzamientosViewModel extends AndroidViewModel {

    private final LanzamientoRepository repo;

    public LanzamientosViewModel(@NonNull Application app) {
        super(app);
        repo = new LanzamientoRepository(app);
    }

    public LiveData<List<LanzamientoEntity>> getLanzamientos() {
        return repo.getProximosLanzamientos();
    }
}
