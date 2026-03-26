package com.miapp.agentegamer.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Entidad que representa un juego en la wishlist del usuario.
 * Almacena ID del juego, nombre, fecha de lanzamiento, imagen, plataforma y precio estimado.
 */
@Entity(tableName = "wishlist")
public class WishlistEntity implements Serializable {

    @PrimaryKey
    private int gameId;

    private String nombre;
    private String fechaLanzamiento;
    private String imagenUrl;
    private String plataforma;
    private double precioEstimado;



    public WishlistEntity(int gameId, String nombre, String fechaLanzamiento, String imagenUrl, String plataforma, double precioEstimado) {
        this.gameId = gameId;
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.imagenUrl = imagenUrl;
        this.plataforma = plataforma;
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

    public String getPlataforma() { return plataforma; }

    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
}
