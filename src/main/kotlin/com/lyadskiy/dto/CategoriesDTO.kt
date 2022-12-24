package com.lyadskiy.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesListDTOResponse(
    val code: Int,
    val list: List<CategoryDTOResponse>
)

@Serializable
data class CategoryDTOResponse(
    val id: Int,
    val name: String
)

@Serializable
data class CategoryDTOReceive(
    val name: String
)