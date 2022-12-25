package com.lyadskiy.features.news

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.newsRouting() {
    post("/v1/news/categories/{categoryId?}/news") {
        val categoryId =
            call.parameters["categoryId"] ?: return@post call.respond(HttpStatusCode.NotFound, "Missing category id")
        val newsController = NewsController(call)
        newsController.createNews(categoryId.toInt())
    }

    get("/v1/news/categories/{categoryId?}/news") {
        val categoryId =
            call.parameters["categoryId"] ?: return@get call.respond(HttpStatusCode.NotFound, "Missing category id")
        val page =
            call.request.queryParameters["page"] ?: return@get call.respond(HttpStatusCode.NotFound, "Missing page")
        val newsController = NewsController(call)
        newsController.getAllNews(page.toInt(), categoryId.toInt())
    }

    get("/v1/news") {
        val id = call.request.queryParameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound, "Missing id")
        val newsController = NewsController(call)
        newsController.getNews(id.toInt())
    }

    put("/v1/news/{id?}") {
        val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.NotFound, "Missing id")
        val newsController = NewsController(call)
        newsController.updateNews(id.toInt())
    }

    delete("/v1/news/{id?}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.NotFound, "Missing id")
        val newsController = NewsController(call)
        newsController.deleteNews(id.toInt())
    }
}