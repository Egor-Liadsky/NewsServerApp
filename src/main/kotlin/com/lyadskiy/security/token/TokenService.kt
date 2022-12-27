package com.lyadskiy.security.token


interface TokenService {

    fun generate(config: TokenConfig, vararg claims: TokenClaim): String
}