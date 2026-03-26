package com.miapp.agentegamer.data.local.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Entidad que representa un lanzamiento próximo de un juego.
 * Almacena el ID del juego, nombre, fecha de lanzamiento y precio estimado.
 */
@Entity(tableName = "lanzamientos", indices = {@Index(value = {"fechaLanzamiento"})})
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
