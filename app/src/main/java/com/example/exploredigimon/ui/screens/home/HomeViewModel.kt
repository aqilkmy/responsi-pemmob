package com.example.exploredigimon.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploredigimon.data.model.DigimonItem
import com.example.exploredigimon.data.repository.DigimonRepository
import com.example.exploredigimon.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: DigimonRepository = DigimonRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<DigimonItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<DigimonItem>>> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        fetchDigimon()
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun searchDigimon() {
        fetchDigimon(_searchQuery.value)
    }

    fun clearSearch() {
        _searchQuery.value = ""
        fetchDigimon()
    }

    fun fetchDigimon(query: String? = null) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val list = repository.getDigimonList(pageSize = 60, name = query)
                _uiState.value = UiState.Success(list)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Gagal memuat data Digimon")
            }
        }
    }
}
