package com.aldev.moviecataloguemade.search

import androidx.lifecycle.*
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.aldev.moviecataloguemade.core.domain.usecase.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    val isSearchQueryValid: LiveData<Boolean> get() = _isSearchQueryValid
    val searchResultLiveData: LiveData<Resource<List<Movie>>> get() = _searchResultLiveData

    private var _isSearchQueryValid: MutableLiveData<Boolean> = MutableLiveData()
    private val _searchResultLiveData: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()

    private var currentSearchQuery = ""
    private var currentSearchType = ""

    fun searchQueryValidation(query: String) {
        _isSearchQueryValid.value = query.trim().isNotBlank()
    }

    fun requestSearchData(searchQuery: String, searchType: String) {
        if (currentSearchQuery != searchQuery || currentSearchType != searchType) {
            currentSearchQuery = searchQuery
            currentSearchType = searchType

            viewModelScope.launch {
                searchUseCase.searchData(searchQuery, searchType).collect {
                    _searchResultLiveData.value = it
                }
            }
        }
    }

    fun getCurrentSearchType(): String = currentSearchType

}