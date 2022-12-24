package com.lyadskiy.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsListDTOResponse(
    val code: Int,
    val list: List<NewsShortDTOResponse>
)

@Serializable
data class NewsDTOReceive(
    val title: String,
    val date: String,
    val shortDescription: String,
    val fullDescription: String
)

@Serializable
data class NewsShortDTOResponse(
    val id: Int,
    val title: String,
    val date: String,
    val shortDescription: String,
)

@Serializable
data class NewsFullDTOResponse(
    val id: Int,
    val title: String,
    val date: String,
    val shortDescription: String,
    val fullDescription: String
)