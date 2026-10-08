package com.example.exploredigimon.data.repository

import com.example.exploredigimon.data.model.DigimonDetailResponse
import com.example.exploredigimon.data.model.DigimonItem
import com.example.exploredigimon.data.network.DigiApiService
import com.example.exploredigimon.data.network.RetrofitClient

class DigimonRepository(
    private val apiService: DigiApiService = RetrofitClient.apiService
) {
    suspend fun getDigimonList(pageSize: Int = 60, page: Int = 0, name: String? = null): List<DigimonItem> {
        val formattedQuery = if (name.isNullOrBlank()) null else name.trim()
        val response = apiService.getDigimonList(pageSize = pageSize, page = page, name = formattedQuery)
        return response.content
    }

    suspend fun getDigimonDetail(id: Int): DigimonDetailResponse {
        return apiService.getDigimonDetail(id)
    }
}
