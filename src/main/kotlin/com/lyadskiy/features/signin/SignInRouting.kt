package com.lyadskiy.features.signin

import com.lyadskiy.database.dao.users.UserDAO
import com.lyadskiy.dto.User
import com.lyadskiy.dto.UserReceive
import com.lyadskiy.security.hasing.HashingService
import com.lyadskiy.security.hasing.SaltedHash
import com.lyadskiy.security.token.TokenClaim
import com.lyadskiy.security.token.TokenConfig
import com.lyadskiy.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.signInRouting(
    hashingService: HashingService,
    userDAO: UserDAO,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    post("signin") {
        val userReceive = call.receiveNullable<UserReceive>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val user = userDAO.getUserByLogin(userReceive.login)

        if (user == null) {
            call.respond(HttpStatusCode.Conflict, "Incorrect login or password")
            return@post
        }

        val isValidPassword = hashingService.verify(
            value = userReceive.password,
            saltedHash = SaltedHash(
                hash = user.login,
                salt = user.salt
            )
        )

//        if (!isValidPassword){
//            call.respond(HttpStatusCode.Conflict, "Incorrect login or password")
//            return@post
//        }

        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(name = "userId", value = user.id.toString())
        )

        call.respond(HttpStatusCode.OK, token)
    }
}