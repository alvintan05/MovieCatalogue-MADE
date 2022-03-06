package com.aldev.moviecataloguemade.di

import com.aldev.moviecataloguemade.core.domain.usecase.favorite.FavoriteInteractor
import com.aldev.moviecataloguemade.core.domain.usecase.favorite.FavoriteUseCase
import com.aldev.moviecataloguemade.core.domain.usecase.movie.MovieInteractor
import com.aldev.moviecataloguemade.core.domain.usecase.movie.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

    @Binds
    @Singleton
    abstract fun provideFavoriteUseCase(favoriteInteractor: FavoriteInteractor): FavoriteUseCase
}