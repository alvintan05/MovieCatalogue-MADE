package com.aldev.moviecataloguemade.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aldev.moviecataloguemade.common.base.BaseFragment
import com.aldev.moviecataloguemade.databinding.FragmentMovieListBinding

class MovieListFragment : BaseFragment<FragmentMovieListBinding>() {

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieListBinding = FragmentMovieListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}