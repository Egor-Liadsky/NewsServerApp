package com.lyadskiy.features.auth

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.authRouting(){
//    authenticate {
//        get("auth") {
//            call.respond(HttpStatusCode.OK)
//        }
//    }
}

fun Route.getSecretInfo() {
//    authenticate {
//        get("secret") {
//            val principal = call.principal<JWTPrincipal>()
//            val userId = principal?.getClaim("userId", String::class)
//            call.respond(HttpStatusCode.OK, "Your userId is $userId")
//        }
//    }
}