package com.lyadskiy.security.token


interface TokenService {
    fun generateJWTToken(config: TokenConfig, vararg claims: TokenClaim): String
}