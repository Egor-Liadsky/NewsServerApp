package com.lyadskiy.features.auth

import com.lyadskiy.database.dao.users.UserDAOImpl
import com.lyadskiy.dto.UserReceive
import com.lyadskiy.security.token.TokenConfig
import com.lyadskiy.security.token.TokenServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.authRouting(tokenConfig: TokenConfig) {

//    val authController by inject<AuthController>()
    val authController = AuthController(UserDAOImpl(), TokenServiceImpl(), tokenConfig)

    post("/register") {
        val userReceive = call.receive<UserReceive>()
        call.respond(HttpStatusCode.OK, authController.registerUser(userReceive))
    }

    post("/login"){
        val userReceive = call.receive<UserReceive>()
        call.respond(HttpStatusCode.OK, authController.loginUser(userReceive))
    }
}