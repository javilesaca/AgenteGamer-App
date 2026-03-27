package com.miapp.agentegamer.data.remote.model;

import java.util.List;

/**
 * DTO para la respuesta de la API de RAWG.
 * Contiene la lista de juegos obtenidos en una página.
 */
public class GamesResponse {

    private List<GameDto> results;

    public List<GameDto> getResults() {
        return results;
    }
}
