package com.lyadskiy.features.signup

import com.lyadskiy.database.dao.users.UserDAO
import com.lyadskiy.dto.User
import com.lyadskiy.dto.UserReceive
import com.lyadskiy.security.hasing.HashingService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.signUpRouting(
    hashingService: HashingService,
    userDAO: UserDAO
) {
    post("signup") {
        val userReceive = call.receiveNullable<UserReceive>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
        val areFieldsBlank = userReceive.login.isBlank() || userReceive.password.isBlank()
        val isPasswordShort = userReceive.password.length < 8

        if (areFieldsBlank || isPasswordShort) {
            call.respond(HttpStatusCode.Conflict)
            return@post
        }

        val saltedHash = hashingService.generatedSaltedHash(userReceive.password)

        val user = User(
            login = userReceive.login,
            password = saltedHash.hash,
            salt = saltedHash.salt
        )
        val wasAcknowlegded = userDAO.registerUser(user)

        if (!wasAcknowlegded){
            call.respond(HttpStatusCode.Conflict)
            return@post
        }

        call.respond(HttpStatusCode.OK)
    }
}