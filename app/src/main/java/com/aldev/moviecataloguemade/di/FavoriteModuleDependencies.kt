package com.aldev.moviecataloguemade.di

import com.aldev.moviecataloguemade.core.domain.usecase.favorite.FavoriteUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun favoriteUseCase(): FavoriteUseCase
}