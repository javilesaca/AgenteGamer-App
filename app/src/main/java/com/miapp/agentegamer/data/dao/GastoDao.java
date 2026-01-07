package com.miapp.agentegamer.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Query;

import com.miapp.agentegamer.data.model.GastoEntity;

import java.util.List;

@Dao
public interface GastoDao {

    @Insert
    void insertGasto(GastoEntity gasto);

    @Update
    void updateGasto(GastoEntity gasto);

    @Delete
    void deleteGasto(GastoEntity gasto);

    @Query("SELECT * FROM gastos ORDER BY fecha DESC")
    LiveData<List<GastoEntity>> getAllGastos();

    @Query("DELETE FROM gastos")
    void deleteAll();

    @Query("SELECT IFNULL(SUM(precio), 0) FROM gastos")
    double getTotalGastado();
}
