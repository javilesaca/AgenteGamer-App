package com.miapp.agentegamer.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lanzamientos")
public class LanzamientoEntity {

    @PrimaryKey
    public int gameId;

    public String nombre;
    public long fechaLanzamiento; //yyyy-MM-dd
    public double precioEstimado;

    public LanzamientoEntity(int gameId, String nombre, long fechaLanzamiento, double precioEstimado) {
        this.gameId = gameId;
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precioEstimado = precioEstimado;
    }

    public int getGameId() {
        return gameId;
    }

    public String getNombre() {
        return nombre;
    }

    public long getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public double getPrecioEstimado() {
        return precioEstimado;
    }
}
