package com.miapp.agentegamer.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wishlist")
public class WishlistEntity {

    @PrimaryKey
    private int gameId;

    private String nombre;
    private String fechaLanzamiento;
    private String imagenUrl;
    private double precioEstimado;

    public WishlistEntity(int gameId, String nombre, String fechaLanzamiento, String imagenUrl, double precioEstimado) {
        this.gameId = gameId;
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagenUrl = imagenUrl;
        this.precioEstimado = precioEstimado;
    }

    //Getters

    public int getGameId() {
        return gameId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public double getPrecioEstimado() {
        return precioEstimado;
    }
}
