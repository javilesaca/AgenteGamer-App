package com.miapp.agentegamer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.miapp.agentegamer.data.model.WishlistEntity;
import com.miapp.agentegamer.data.repository.WishlistRepository;

import java.util.List;

public class WishlistViewModel extends AndroidViewModel {

    private final WishlistRepository repo;

    public WishlistViewModel(@NonNull Application app) {
        super(app);
        repo = new WishlistRepository(app);
    }

    public void insertar(WishlistEntity juego) {
        repo.insertar(juego);
    }

    public LiveData<List<WishlistEntity>> getWishlist() {
        return repo.getWishlist();
    }
}
