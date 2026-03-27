package com.miapp.agentegamer.data.remote.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Cliente singleton para Retrofit.
 * Proporciona instancia de Retrofit configurada para la API de RAWG.
 */
public class RetrofitClient {

    private static final  String BASE_URL = "https://api.rawg.io/api/";
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
