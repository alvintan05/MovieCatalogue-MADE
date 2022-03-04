package com.aldev.moviecataloguemade.core.di

import com.aldev.moviecataloguemade.core.data.MovieRepositoryImpl
import com.aldev.moviecataloguemade.core.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}