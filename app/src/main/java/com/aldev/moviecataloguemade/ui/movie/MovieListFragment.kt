package com.aldev.moviecataloguemade.ui.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.aldev.moviecataloguemade.common.base.BaseFragment
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.databinding.FragmentMovieListBinding
import com.aldev.moviecataloguemade.detail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding>() {

    private val viewModel: MovieListViewModel by viewModels()
    private val movieListAdapter = MovieListAdapter()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieListBinding = FragmentMovieListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setupRecyclerView()
            observeLiveData()
        }
    }

    private fun setupRecyclerView() {
        binding?.rvMovies?.apply {
            setHasFixedSize(true)
            adapter = movieListAdapter
        }

        movieListAdapter.onItemClick = { movie ->
            val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
            intent.putExtras(
                bundleOf().apply {
                    putInt(CommonConstant.DetailIntentExtra.EXTRA_ID, movie.id)
                    putString(
                        CommonConstant.DetailIntentExtra.EXTRA_TYPE,
                        CommonConstant.MovieType.MOVIE
                    )
                    putString(
                        CommonConstant.DetailIntentExtra.EXTRA_SOURCE,
                        CommonConstant.DataSource.REMOTE
                    )
                }
            )
            startActivity(intent)
        }
    }

    override fun observeLiveData() {
        viewModel.movieListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                    binding?.rvMovies?.visibility = View.GONE
                    Log.d("TAG", "loading")
                }
                is Resource.Success -> {
                    binding?.progressBar?.visibility = View.GONE
                    binding?.rvMovies?.visibility = View.VISIBLE
                    movieListAdapter.setData(it.data)
                    Log.d("TAG", "success")
                }
                is Resource.Error -> {
                    binding?.progressBar?.visibility = View.GONE
                    binding?.rvMovies?.visibility = View.GONE
                    Log.d("TAG", it.message ?: "error")
                }
            }
        }
    }

}