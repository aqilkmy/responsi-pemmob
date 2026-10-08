package com.example.exploredigimon.data.model

data class DigimonDetailResponse(
    val id: Int = 0,
    val name: String = "",
    val xAntibody: Boolean = false,
    val images: List<DigimonImage> = emptyList(),
    val levels: List<DigimonLevel> = emptyList(),
    val types: List<DigimonType> = emptyList(),
    val attributes: List<DigimonAttribute> = emptyList(),
    val descriptions: List<DigimonDescription> = emptyList(),
    val skills: List<DigimonSkill> = emptyList(),
    val priorEvolutions: List<DigimonEvolution> = emptyList(),
    val nextEvolutions: List<DigimonEvolution> = emptyList()
)

data class DigimonImage(
    val href: String = "",
    val transparent: Boolean = false
)

data class DigimonLevel(
    val id: Int? = null,
    val level: String = ""
)

data class DigimonType(
    val id: Int? = null,
    val type: String = ""
)

data class DigimonAttribute(
    val id: Int? = null,
    val attribute: String = ""
)

data class DigimonDescription(
    val origin: String? = null,
    val language: String? = null,
    val description: String = ""
)

data class DigimonSkill(
    val id: Int? = null,
    val skill: String = "",
    val translation: String? = null,
    val description: String? = null
)

data class DigimonEvolution(
    val id: Int? = null,
    val digimon: String = "",
    val condition: String? = null,
    val image: String? = null,
    val url: String? = null
)
