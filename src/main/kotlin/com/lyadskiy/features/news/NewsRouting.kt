package com.lyadskiy.features.news

import com.lyadskiy.dto.NewsDTOReceive
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.newsRouting() {

    val newsController by inject<NewsController>()

    post("/v1/news/categories/{categoryId?}/news") {
        val categoryId = call.parameters["categoryId"] ?: return@post call.respond(HttpStatusCode.NotFound, "Missing category id")
        val newsReceive = call.receive<NewsDTOReceive>()
        newsController.createNews(categoryId.toInt(), newsReceive)
        call.respond(HttpStatusCode.Created, "News created")
    }

    get("/v1/news/categories/{categoryId?}/news") {
        val categoryId = call.parameters["categoryId"] ?: return@get call.respond(HttpStatusCode.NotFound, "Missing category id")
        val page = call.request.queryParameters["page"] ?: return@get call.respond(HttpStatusCode.NotFound, "Missing page")
        call.respond(HttpStatusCode.OK, newsController.getAllNews(page.toInt(), categoryId.toInt()))
    }

    get("/v1/news") {
        val id = call.request.queryParameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound, "Missing id")
        call.respond(HttpStatusCode.OK, newsController.getNews(id.toInt()))
    }

    put("/v1/news/{id?}") {
        val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.NotFound, "Missing id")
        val newsReceive = call.receive<NewsDTOReceive>()
        newsController.updateNews(id.toInt(), newsReceive)
        call.respond(HttpStatusCode.OK, "News updated")
    }

    delete("/v1/news/{id?}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.NotFound, "Missing id")
        newsController.deleteNews(id.toInt())
        call.respond(HttpStatusCode.OK, "News deleted")
    }
}