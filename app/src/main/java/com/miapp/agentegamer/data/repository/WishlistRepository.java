package com.miapp.agentegamer.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.miapp.agentegamer.data.dao.WishlistDao;
import com.miapp.agentegamer.data.database.AppDatabase;
import com.miapp.agentegamer.data.model.WishlistEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WishlistRepository {

    private final WishlistDao dao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public WishlistRepository(Application app) {
        AppDatabase db = AppDatabase.getInstance(app);
        dao = db.wishlistDao();
    }

    public LiveData<List<WishlistEntity>> getWishlist() {
        return dao.getWhishlist();
    }

    public void insertar(WishlistEntity juego) {
        executor.execute(() -> dao.insert(juego));
    }

    public void actualizar(WishlistEntity juego) {
        executor.execute(() -> dao.actualizar(juego));
    }
}
