package com.aldev.moviecataloguemade.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.aldev.moviecataloguemade.common.base.BaseFragment
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.common.gone
import com.aldev.moviecataloguemade.common.visible
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.detail.DetailMovieActivity
import com.aldev.moviecataloguemade.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()
    private var searchAdapter: SearchAdapter? = null

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
        searchAdapter = SearchAdapter()
        binding?.rvSearch?.apply {
            setHasFixedSize(true)
            adapter = searchAdapter
        }

        searchAdapter?.onItemClick = { movie ->
            val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
            intent.putExtras(
                bundleOf().apply {
                    putInt(CommonConstant.DetailIntentExtra.EXTRA_ID, movie.id)
                    putString(
                        CommonConstant.DetailIntentExtra.EXTRA_TYPE,
                        viewModel.getCurrentSearchType()
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
            hideKeyboard()
            val query = binding?.edtSearch?.text.toString().trim()
            var searchType = ""
            val selectedId = binding?.radioGroupType?.checkedRadioButtonId

            if (selectedId != -1) {
                searchType = getRadioButtonText(selectedId)
            }
            viewModel.requestSearchData(query, searchType)
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

        viewModel.searchResultLiveData.observe(viewLifecycleOwner) { searchResult ->
            when (searchResult) {
                is Resource.Loading -> {
                    binding?.progressBar?.visible()
                    binding?.rvSearch?.gone()
                }
                is Resource.Success -> {
                    binding?.progressBar?.gone()
                    binding?.rvSearch?.visible()

                    searchAdapter?.setData(searchResult.data)
                    binding?.rvSearch?.scrollToPosition(0)
                }
                is Resource.Error -> {
                    binding?.progressBar?.gone()
                    binding?.rvSearch?.gone()
                    binding?.tvError?.visible()

                    if (searchResult.message == CommonConstant.RESPONSE_EMPTY) {
                        binding?.tvError?.text =
                            getString(com.aldev.moviecataloguemade.common.R.string.not_found_label)
                    } else {
                        binding?.tvError?.text =
                            getString(com.aldev.moviecataloguemade.common.R.string.error_label)
                    }

                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding?.root?.applicationWindowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rvSearch?.adapter = null
        searchAdapter = null
    }
}