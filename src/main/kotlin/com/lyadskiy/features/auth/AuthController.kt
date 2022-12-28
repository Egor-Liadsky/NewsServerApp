package com.lyadskiy.features.auth

import com.lyadskiy.database.dao.users.UserDAOImpl
import com.lyadskiy.dto.UserReceive
import com.lyadskiy.security.token.TokenClaim
import com.lyadskiy.security.token.TokenConfig
import com.lyadskiy.security.token.TokenServiceImpl

class AuthController(
    private val userDAO: UserDAOImpl,
    private val tokenService: TokenServiceImpl,
    private val tokenConfig: TokenConfig
) {

    suspend fun registerUser(userReceive: UserReceive): String {
        userDAO.registerUser(userReceive)
        return tokenService.generateJWTToken(
            tokenConfig,
            TokenClaim(name = "username", value = userReceive.username),
            TokenClaim(name = "password", value = userReceive.password)
        )
    }

    suspend fun loginUser(userReceive: UserReceive): String {
        val user = userDAO.authUser(userReceive)
        return if (userReceive.password == user.password) {
            val token = tokenService.generateJWTToken(
                tokenConfig,
                TokenClaim(name = "username", value = userReceive.username),
                TokenClaim(name = "password", value = userReceive.password)
            )
            token
        } else {
            "Incorrect login or password"
        }
    }
}