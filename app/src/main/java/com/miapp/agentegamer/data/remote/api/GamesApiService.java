package com.miapp.agentegamer.data.remote.api;

import com.miapp.agentegamer.data.remote.model.GamesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GamesApiService {

    @GET("games")
    Call<GamesResponse> getGames(
        @Query("key") String apiKey,
        @Query("ordering") String ordering,
        @Query("page_size") int pageSize
    );
}
