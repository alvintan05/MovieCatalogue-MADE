package com.aldev.moviecataloguemade.di

import com.aldev.moviecataloguemade.core.domain.usecase.detail.DetailInteractor
import com.aldev.moviecataloguemade.core.domain.usecase.detail.DetailUseCase
import com.aldev.moviecataloguemade.core.domain.usecase.movie.MovieInteractor
import com.aldev.moviecataloguemade.core.domain.usecase.movie.MovieUseCase
import com.aldev.moviecataloguemade.core.domain.usecase.search.SearchInteractor
import com.aldev.moviecataloguemade.core.domain.usecase.search.SearchUseCase
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
    abstract fun provideDetailUseCase(detailInteractor: DetailInteractor): DetailUseCase

    @Binds
    @Singleton
    abstract fun provideSearchUseCase(searchInteractor: SearchInteractor): SearchUseCase
}