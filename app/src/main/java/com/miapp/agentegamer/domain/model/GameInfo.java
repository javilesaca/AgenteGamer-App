package com.miapp.agentegamer.domain.model;

/**
 * Modelo de dominio para información básica de un juego.
 * Contiene nombre y rating del juego.
 */
public class GameInfo {

    private final String name;
    private final double rating;

    public GameInfo(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() { return name; }
    public double getRating() { return rating; }
}
