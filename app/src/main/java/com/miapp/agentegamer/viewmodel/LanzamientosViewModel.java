package com.miapp.agentegamer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.miapp.agentegamer.data.model.LanzamientoEntity;
import com.miapp.agentegamer.data.remote.api.GamesApiService;
import com.miapp.agentegamer.data.remote.api.RetrofitClient;
import com.miapp.agentegamer.data.remote.model.GameDto;
import com.miapp.agentegamer.data.remote.model.GamesResponse;
import com.miapp.agentegamer.data.repository.LanzamientoRepository;
import com.miapp.agentegamer.util.FechaUtils;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LanzamientosViewModel extends AndroidViewModel {

    private final LanzamientoRepository repo;
    private final GamesApiService api;

    public LanzamientosViewModel(@NonNull Application app) {
        super(app);
        repo = new LanzamientoRepository(app);
        api = RetrofitClient.getInstance().create(GamesApiService.class);
    }

    public LiveData<List<LanzamientoEntity>> getLanzamientos() {
        return repo.getProximosLanzamientos();
    }

    public void precargaProximos15Dias(String apiKey) {

        String fechas = FechaUtils.hoy()+","+FechaUtils.dentroDeDias(15);


        api.getFutureGames(apiKey,fechas,"released",20).enqueue(new Callback<GamesResponse>() {
            @Override
            public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    for (GameDto juego : response.body().getResults()) {

                        Date fechaLanzamiento = FechaUtils.parseFecha(juego.getReleaseDate());

                        LanzamientoEntity l = new LanzamientoEntity(
                                juego.getId(),
                                juego.getName(),
                                fechaLanzamiento.getTime(),
                                0.00
                        );

                        repo.insertar(l);
                    }
                }
            }

            @Override
            public void onFailure(Call<GamesResponse> call, Throwable throwable) {
                    //Log opcional
            }
        });
    }
}
