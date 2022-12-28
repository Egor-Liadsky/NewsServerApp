package com.lyadskiy

import com.lyadskiy.database.DatabaseFactory
import com.lyadskiy.plugins.*
import com.lyadskiy.security.token.TokenConfig
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init()

    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET"),
        realm = environment.config.property("jwt.realm").getString()
    )

    configureKoin()
    configureMonitoring()
    configureGraphQL()
    configureSerialization()
    configureRouting(tokenConfig)
    configureSecurity(tokenConfig)
}