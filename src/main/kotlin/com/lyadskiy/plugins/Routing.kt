package com.lyadskiy.plugins

import com.lyadskiy.database.dao.users.UserDAO
import com.lyadskiy.features.auth.authRouting
import com.lyadskiy.features.auth.getSecretInfo
import com.lyadskiy.features.categories.categoriesRouting
import com.lyadskiy.features.news.newsRouting
import com.lyadskiy.features.signin.signInRouting
import com.lyadskiy.features.signup.signUpRouting
import com.lyadskiy.security.hasing.HashingService
import com.lyadskiy.security.token.TokenConfig
import com.lyadskiy.security.token.TokenService
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting(
    hashingService: HashingService,
    userDAO: UserDAO,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {

    routing {
        signInRouting(hashingService, userDAO, tokenService, tokenConfig)
        signUpRouting(hashingService, userDAO)
        getSecretInfo()
        authRouting()
        categoriesRouting()
        newsRouting()
    }
}
