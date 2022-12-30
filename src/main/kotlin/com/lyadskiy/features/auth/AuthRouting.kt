package com.lyadskiy.features.auth

import com.lyadskiy.database.dao.users.UserDAO
import com.lyadskiy.dto.UserReceive
import com.lyadskiy.security.token.TokenClaim
import com.lyadskiy.security.token.TokenConfig
import com.lyadskiy.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRouting(tokenConfig: TokenConfig, userDAO: UserDAO, tokenService: TokenService) {

    post("/register") {
        val userReceive = call.receive<UserReceive>()

        userDAO.registerUser(userReceive)
        val token = tokenService.generateJWTToken(
            tokenConfig,
            TokenClaim(name = "username", value = userReceive.username),
            TokenClaim(name = "password", value = userReceive.password)
        )
        call.respond(HttpStatusCode.OK, token)
    }

    post("/login") {
        val userReceive = call.receive<UserReceive>()
        try {
            val user = userDAO.authUser(userReceive)
            if (userReceive.password == user.password) {
                val token = tokenService.generateJWTToken(
                    tokenConfig,
                    TokenClaim(name = "username", value = userReceive.username),
                    TokenClaim(name = "password", value = userReceive.password)
                )
                call.respond(HttpStatusCode.OK, token)
            } else {
                call.respond(HttpStatusCode.Conflict, "Invalid password")
            }
        } catch (ex: Exception) {
            call.respond(HttpStatusCode.Conflict, "A user with this username was not found")
        }
    }
}

