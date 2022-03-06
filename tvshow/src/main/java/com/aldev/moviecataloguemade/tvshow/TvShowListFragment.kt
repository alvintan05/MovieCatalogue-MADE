package com.aldev.moviecataloguemade.tvshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.aldev.moviecataloguemade.common.base.BaseFragment
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.tvshow.databinding.FragmentTvShowListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowListFragment : BaseFragment<FragmentTvShowListBinding>() {

    private val viewModel: TvShowListViewModel by viewModels()
    private val tvShowListAdapter = TvShowListAdapter()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTvShowListBinding = FragmentTvShowListBinding.inflate(inflater, container, false)

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
            adapter = tvShowListAdapter
        }
    }

    private fun observeLiveData() {
        viewModel.tvShowListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                    binding?.rvMovies?.visibility = View.GONE
                    Log.d("TAG", "loading")
                }
                is Resource.Success -> {
                    binding?.progressBar?.visibility = View.GONE
                    binding?.rvMovies?.visibility = View.VISIBLE
                    tvShowListAdapter.setData(it.data)
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