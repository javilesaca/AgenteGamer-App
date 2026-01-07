package com.miapp.agentegamer.data.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.miapp.agentegamer.data.dao.GastoDao;
import com.miapp.agentegamer.data.dao.WishlistDao;
import com.miapp.agentegamer.data.database.AppDatabase;
import com.miapp.agentegamer.data.model.GastoEntity;
import com.miapp.agentegamer.data.model.WishlistEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WishlistRepository {

    private final WishlistDao dao;
    private final GastoDao gastoDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public WishlistRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context.getApplicationContext());
        dao = db.wishlistDao();
        gastoDao = db.gastoDao();
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

    public List<WishlistEntity> getWishlistSync() {
        return  dao.getWishlistSync();
    }

    public double getTotalGastado() {
        return dao.getTotalGastado();
    }

    public void insertarGasto(GastoEntity gasto) { executor.execute(() -> gastoDao.insertGasto(gasto)); }

    public void borrar(WishlistEntity juego) { executor.execute(() -> dao.borrar(juego));}
}
