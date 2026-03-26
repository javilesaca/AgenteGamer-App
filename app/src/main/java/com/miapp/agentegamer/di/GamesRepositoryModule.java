package com.miapp.agentegamer.di;

import com.miapp.agentegamer.BuildConfig;
import com.miapp.agentegamer.data.remote.repository.GamesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * Módulo de Hilt para proporcionar el repositorio de juegos.
 * Instancia GamesRepository con la API key de RAWG.
 */
@Module
@InstallIn(SingletonComponent.class)
public class GamesRepositoryModule {

    @Provides
    @Singleton
    public static GamesRepository provideGamesRepository() {
        return new GamesRepository(BuildConfig.RAWG_API_KEY);
    }
}
