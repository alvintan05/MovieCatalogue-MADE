package com.aldev.moviecataloguemade.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aldev.moviecataloguemade.common.base.BaseFragment
import com.aldev.moviecataloguemade.di.FavoriteModuleDependencies
import com.aldev.moviecataloguemade.favorite.databinding.FragmentFavoriteListBinding
import com.aldev.moviecataloguemade.favorite.di.DaggerFavoriteComponent
import dagger.hilt.android.EntryPointAccessors

class FavoriteListFragment : BaseFragment<FragmentFavoriteListBinding>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteListBinding = FragmentFavoriteListBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}