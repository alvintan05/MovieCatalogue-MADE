package com.aldev.moviecataloguemade.favorite.ui

import android.content.Context
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
import com.aldev.moviecataloguemade.detail.DetailMovieActivity
import com.aldev.moviecataloguemade.di.FavoriteModuleDependencies
import com.aldev.moviecataloguemade.favorite.databinding.FragmentFavoriteListBinding
import com.aldev.moviecataloguemade.favorite.di.DaggerFavoriteComponent
import com.aldev.moviecataloguemade.favorite.utils.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteListFragment : BaseFragment<FragmentFavoriteListBinding>() {


    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteListViewModel by viewModels {
        factory
    }

    private val favoriteListAdapter = FavoriteListAdapter()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteListBinding = FragmentFavoriteListBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setupRecyclerView()
            observeLiveData()
        }
    }

    private fun setupRecyclerView() {
        binding?.rvFavorite?.apply {
            setHasFixedSize(true)
            adapter = favoriteListAdapter
        }

        favoriteListAdapter.onItemClick = { movie ->
            val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
            intent.putExtras(
                bundleOf().apply {
                    putInt(CommonConstant.DetailIntentExtra.EXTRA_ID, movie.id)
                    putString(
                        CommonConstant.DetailIntentExtra.EXTRA_TYPE,
                        movie.type
                    )
                    putString(
                        CommonConstant.DetailIntentExtra.EXTRA_SOURCE,
                        CommonConstant.DataSource.LOCAL
                    )
                }
            )
            startActivity(intent)
        }
    }

    private fun observeLiveData() {
        viewModel.favoriteListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                    binding?.rvFavorite?.visibility = View.GONE
                    Log.d("TAG", "loading")
                }
                is Resource.Success -> {
                    binding?.progressBar?.visibility = View.GONE
                    binding?.rvFavorite?.visibility = View.VISIBLE
                    favoriteListAdapter.setData(it.data)
                    Log.d("TAG", "success")
                }
                is Resource.Error -> {
                    binding?.progressBar?.visibility = View.GONE
                    binding?.rvFavorite?.visibility = View.GONE
                    Log.d("TAG", it.message ?: "error")
                }
            }
        }
    }

}