package com.miapp.agentegamer.data.remote.api;

import com.miapp.agentegamer.data.remote.model.GamesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interfaz de servicio para la API de RAWG.
 * Define los endpoints para obtener y buscar juegos.
 */
public interface GamesApiService {

    @GET("games")
    Call<GamesResponse> getGames(
        @Query("key") String apiKey,
        @Query("ordering") String ordering,
        @Query("page_size") int pageSize
    );

    @GET("games")
    Call<GamesResponse> searchGames(
            @Query("key") String apiKey,
            @Query("search") String texto,
            @Query("page") int page,
            @Query("page_size") int pageSize
    );

    @GET("games")
    Call<GamesResponse> getFutureGames(
            @Query("key") String apiKey,
            @Query("dates") String dates,
            @Query("ordering") String ordering,
            @Query("page_size") int pageSize
    );
}
