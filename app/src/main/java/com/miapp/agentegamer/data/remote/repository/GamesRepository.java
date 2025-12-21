package com.miapp.agentegamer.data.remote.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.miapp.agentegamer.data.remote.api.GamesApiService;
import com.miapp.agentegamer.data.remote.api.RetrofitClient;
import com.miapp.agentegamer.data.remote.model.GameDto;
import com.miapp.agentegamer.data.remote.model.GamesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GamesRepository {

    private final GamesApiService apiService;
    private final MutableLiveData<List<GameDto>> gamesLiveData = new MutableLiveData<>();
    private int currentPage = 1;
    private boolean isLoading = false;
    private final List<GameDto> acumulado = new ArrayList<>();

    public GamesRepository(){
        apiService = RetrofitClient.getInstance()
                .create(GamesApiService.class);
    }

    public LiveData<List<GameDto>> getGames(String apiKey) {
        apiService.getGames(apiKey, "-rating", 10).enqueue(new Callback<GamesResponse>() {
            @Override
            public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    gamesLiveData.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<GamesResponse> call, Throwable throwable) {
                gamesLiveData.setValue(null);
            }
        });

        return gamesLiveData;
    }

/*    public LiveData<List<GameDto>> searchGames(String apiKey, String texto) {
        MutableLiveData<List<GameDto>> data = new MutableLiveData<>();

        apiService.searchGames(apiKey, texto, 20)
                .enqueue(new Callback<GamesResponse>() {
                    @Override
                    public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            data.setValue(response.body().getResults());
                        } else {
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<GamesResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }*/

    public LiveData<List<GameDto>> buscarJuegosPaginados(String apiKey, String query, boolean reset, MutableLiveData<Boolean> cargando) {
        MutableLiveData<List<GameDto>> data = new MutableLiveData<>();

        if (isLoading) return data;

        if (reset) {
            currentPage = 1;
            acumulado.clear();
        }

        isLoading = true;
        cargando.postValue(true);

        apiService.searchGames(apiKey, query, currentPage, 20)
                .enqueue(new Callback<GamesResponse>() {
                    @Override
                    public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
                        isLoading = false;
                        cargando.postValue(false);

                        if(response.isSuccessful() && response.body() != null) {
                            acumulado.addAll(response.body().getResults());
                            currentPage++;
                            data.setValue(acumulado);
                        }
                    }

                    @Override
                    public void onFailure(Call<GamesResponse> call, Throwable throwable) {
                        isLoading = false;
                        cargando.postValue(false);
                    }
                });
        return data;
    }
}
