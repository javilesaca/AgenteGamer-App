package com.miapp.agentegamer.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gastos")
public class GastoEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombreJuego;
    private double precio;
    private long fecha;  // Guardada en milisegundos

    // Constructor
    public GastoEntity(String nombreJuego, double precio, long fecha) {
        this.nombreJuego = nombreJuego;
        this.precio = precio;
        this.fecha = fecha;
    }

    // GETTERS Y SETTERS
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombreJuego() { return nombreJuego; }

    public void setNombreJuego(String nombreJuego) { this.nombreJuego = nombreJuego; }

    public double getPrecio() { return precio; }

    public void setPrecio(double precio) { this.precio = precio; }

    public long getFecha() { return fecha; }

    public void setFecha(long fecha) { this.fecha = fecha; }
}
