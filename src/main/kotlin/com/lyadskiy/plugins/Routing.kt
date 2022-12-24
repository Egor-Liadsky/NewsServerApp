package com.lyadskiy.plugins

import com.lyadskiy.features.categories.categoriesRouting
import com.lyadskiy.features.news.newsRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        categoriesRouting()
        newsRouting()
    }
}
