package com.miapp.agentegamer.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.miapp.agentegamer.data.dao.GastoDao;
import com.miapp.agentegamer.data.model.GastoEntity;

@Database(entities = {GastoEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GastoDao gastoDao();
}
