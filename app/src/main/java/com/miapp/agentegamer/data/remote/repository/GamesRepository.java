package com.miapp.agentegamer.data.remote.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.miapp.agentegamer.data.remote.api.GamesApiService;
import com.miapp.agentegamer.data.remote.api.RetrofitClient;
import com.miapp.agentegamer.data.remote.model.GameDto;
import com.miapp.agentegamer.data.remote.model.GamesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GamesRepository {

    private final GamesApiService apiService;
    private final MutableLiveData<List<GameDto>> gamesLiveData = new MutableLiveData<>();

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
}
