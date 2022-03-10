package com.aldev.moviecataloguemade.search

import androidx.lifecycle.*
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.aldev.moviecataloguemade.core.domain.usecase.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    val isSearchQueryValid get() = _isSearchQueryValid

    private var _isSearchQueryValid: MutableLiveData<Boolean> = MutableLiveData()
    var searchResultLiveData: LiveData<Resource<List<Movie>>> = MutableLiveData()

    private var currentSearchQuery = ""
    var currentSearchType = ""

    fun searchQueryValidation(query: String) {
        _isSearchQueryValid.value = query.trim().isNotBlank()
    }

    fun requestSearchData(searchQuery: String, searchType: String) {
        if (currentSearchQuery != searchQuery || currentSearchType != searchType) {
            currentSearchQuery = searchQuery
            currentSearchType = searchType
            viewModelScope.launch {
                searchResultLiveData = searchUseCase.searchData(searchQuery, searchType).asLiveData()
            }
        }
    }

}