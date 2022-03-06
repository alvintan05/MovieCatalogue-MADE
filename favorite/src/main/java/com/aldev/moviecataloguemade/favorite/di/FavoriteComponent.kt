package com.aldev.moviecataloguemade.favorite.di

import android.content.Context
import com.aldev.moviecataloguemade.di.FavoriteModuleDependencies
import com.aldev.moviecataloguemade.favorite.FavoriteListFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteListFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }

}