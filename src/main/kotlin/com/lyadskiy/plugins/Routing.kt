package com.lyadskiy.plugins

import com.lyadskiy.features.auth.authRouting
import com.lyadskiy.features.categories.categoriesRouting
import com.lyadskiy.features.news.newsRouting
import com.lyadskiy.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(tokenConfig: TokenConfig) {

    routing {
        authRouting(tokenConfig)
        categoriesRouting()
        newsRouting()
    }
}
