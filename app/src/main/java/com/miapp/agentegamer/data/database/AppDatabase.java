package com.miapp.agentegamer.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.miapp.agentegamer.data.dao.GastoDao;
import com.miapp.agentegamer.data.dao.WishlistDao;
import com.miapp.agentegamer.data.model.GastoEntity;
import com.miapp.agentegamer.data.model.WishlistEntity;

@Database(entities = {GastoEntity.class, WishlistEntity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract GastoDao gastoDao();
    public abstract WishlistDao wishlistDao();


    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "agente_gamer_db"
                    ).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}

