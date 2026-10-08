package com.example.exploredigimon.data.network

import com.example.exploredigimon.data.model.DigimonDetailResponse
import com.example.exploredigimon.data.model.DigimonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DigiApiService {
    @GET("api/v1/digimon")
    suspend fun getDigimonList(
        @Query("pageSize") pageSize: Int = 60,
        @Query("page") page: Int = 0,
        @Query("name") name: String? = null
    ): DigimonListResponse

    @GET("api/v1/digimon/{id}")
    suspend fun getDigimonDetail(
        @Path("id") id: Int
    ): DigimonDetailResponse
}
