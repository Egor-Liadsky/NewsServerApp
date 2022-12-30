package com.lyadskiy.plugins

import com.lyadskiy.database.dao.users.UserDAOImpl
import com.lyadskiy.features.auth.authRouting
import com.lyadskiy.features.categories.categoriesRouting
import com.lyadskiy.features.news.newsRouting
import com.lyadskiy.security.token.TokenConfig
import com.lyadskiy.security.token.TokenServiceImpl
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    tokenConfig: TokenConfig, userDAO: UserDAOImpl,
    tokenService: TokenServiceImpl,
) {

    routing {
        authRouting(tokenConfig, userDAO, tokenService)
        categoriesRouting()
        newsRouting()
    }
}
