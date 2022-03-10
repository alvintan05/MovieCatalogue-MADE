package com.aldev.moviecataloguemade.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.aldev.moviecataloguemade.common.base.BaseFragment
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.detail.DetailMovieActivity
import com.aldev.moviecataloguemade.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()
    private val searchAdapter = SearchAdapter()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setupRecyclerView()
            setupSearchInput()
            observeLiveData()
        }
    }

    private fun setupRecyclerView() {
        binding?.rvSearch?.apply {
            setHasFixedSize(true)
            adapter = searchAdapter
        }

        searchAdapter.onItemClick = { movie ->
            val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
            intent.putExtras(
                bundleOf().apply {
                    putInt(CommonConstant.DetailIntentExtra.EXTRA_ID, movie.id)
                    putString(
                        CommonConstant.DetailIntentExtra.EXTRA_TYPE,
                        viewModel.currentSearchType
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

    private fun setupSearchInput() {
        binding?.edtSearch?.doAfterTextChanged {
            viewModel.searchQueryValidation(it.toString())
        }

        binding?.btnSearch?.setOnClickListener {
            val query = binding?.edtSearch?.text.toString().trim()
            var searchType = ""
            val selectedId = binding?.radioGroupType?.checkedRadioButtonId

            if (selectedId != -1) {
                searchType = getRadioButtonText(selectedId)
            }
            viewModel.requestSearchData(query, searchType)

            viewModel.searchResultLiveData.observe(viewLifecycleOwner){
                when (it) {
                    is Resource.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                        binding?.rvSearch?.visibility = View.GONE
                        Log.d("TAG", "loading")
                    }
                    is Resource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.rvSearch?.visibility = View.VISIBLE
                        searchAdapter.setData(it.data)
                        Log.d("TAG", "success")
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.rvSearch?.visibility = View.GONE
                        Log.d("TAG", it.message ?: "error")
                    }
                }
            }
        }
    }

    private fun getRadioButtonText(id: Int?): String {
        return when (id) {
            R.id.rb_movie -> CommonConstant.MovieType.MOVIE
            else -> CommonConstant.MovieType.TV_SHOW
        }
    }

    override fun observeLiveData() {
        viewModel.isSearchQueryValid.observe(viewLifecycleOwner) {
            binding?.btnSearch?.isEnabled = it
        }


    }

}