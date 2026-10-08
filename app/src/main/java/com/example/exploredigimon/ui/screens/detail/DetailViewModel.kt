package com.example.exploredigimon.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exploredigimon.data.model.DigimonDetailResponse
import com.example.exploredigimon.data.repository.DigimonRepository
import com.example.exploredigimon.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: DigimonRepository = DigimonRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<DigimonDetailResponse>>(UiState.Loading)
    val uiState: StateFlow<UiState<DigimonDetailResponse>> = _uiState.asStateFlow()

    fun fetchDetail(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val detail = repository.getDigimonDetail(id)
                _uiState.value = UiState.Success(detail)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Gagal memuat detail Digimon")
            }
        }
    }
}
