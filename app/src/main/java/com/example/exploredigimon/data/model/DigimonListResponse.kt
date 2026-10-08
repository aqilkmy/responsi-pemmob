package com.example.exploredigimon.data.model

data class DigimonListResponse(
    val content: List<DigimonItem> = emptyList(),
    val pageable: PageableInfo? = null
)

data class PageableInfo(
    val currentPage: Int = 0,
    val elementsOnPage: Int = 0,
    val totalElements: Int = 0,
    val totalPages: Int = 0,
    val previousPage: String? = null,
    val nextPage: String? = null
)
