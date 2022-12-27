package com.lyadskiy

import com.lyadskiy.database.DatabaseFactory
import com.lyadskiy.database.dao.users.UserDAOImpl
import com.lyadskiy.plugins.*
import com.lyadskiy.security.hasing.HashingServiceImpl
import com.lyadskiy.security.token.TokenConfig
import com.lyadskiy.security.token.TokenServiceImpl
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init()

    val tokenService = TokenServiceImpl()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
    )

    val hashingService = HashingServiceImpl()

    configureKoin()
    configureMonitoring()
    configureGraphQL()
    configureSerialization()
    configureRouting(hashingService, UserDAOImpl(), tokenService, tokenConfig)
    configureSecurity(tokenConfig)
}