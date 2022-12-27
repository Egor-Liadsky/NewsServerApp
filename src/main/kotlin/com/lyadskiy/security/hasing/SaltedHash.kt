package com.lyadskiy.security.hasing

data class SaltedHash(
    val hash: String,
    val salt: String
)