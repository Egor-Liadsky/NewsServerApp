package com.lyadskiy.plugins

import com.lyadskiy.features.categories.categoriesRouting
import com.lyadskiy.features.news.newsRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    routing {
        categoriesRouting()
        newsRouting()
    }
}
