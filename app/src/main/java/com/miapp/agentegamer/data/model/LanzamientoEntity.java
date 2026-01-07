package com.miapp.agentegamer.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lanzamientos")
public class LanzamientoEntity {

    @PrimaryKey
    public int gameId;

    public String nombre;
    public String fechaLanzamiento; //yyyy-MM-dd
    public double precioEstimado;

    public LanzamientoEntity(int gameId, String nombre, String fechaLanzamiento, double precioEstimado) {
        this.gameId = gameId;
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precioEstimado = precioEstimado;
    }
}
