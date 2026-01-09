package com.miapp.agentegamer.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.miapp.agentegamer.data.model.LanzamientoEntity;

import java.util.List;

@Dao
public interface LanzamientoDao {

    @Query("SELECT * FROM lanzamientos ORDER BY fechaLanzamiento ASC")
    LiveData<List<LanzamientoEntity>> getProximosLanzamientos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertar(LanzamientoEntity lanzamiento);

    @Query("DELETE FROM lanzamientos")
    void borrarTodos();
}
