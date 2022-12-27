package com.lyadskiy.dto

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val login: String,
    val password: String,
    val salt: String
)

@Serializable
data class UserReceive(
    val login: String,
    val password: String,
)

@Serializable

data class UserResponse(
    val id: Int,
    val login: String,
    val password: String,
    val salt: String
)