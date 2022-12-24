package com.lyadskiy.features.categories

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.categoriesRouting(){

    post("/v1/news/categories") {
        val categoriesController = CategoriesController(call)
        categoriesController.createCategory()
    }

    get("/v1/news/categories") {
        val categoriesController = CategoriesController(call)
        categoriesController.getCategories()
    }

    put("/v1/news/categories/{id?}") {
        val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.NotFound, "Missing id")
        val categoriesController = CategoriesController(call)
        categoriesController.updateCategories(id.toInt())
    }

    delete("/v1/news/categories/{id?}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.NotFound, "Missing id")
        val categoriesController = CategoriesController(call)
        categoriesController.deleteCategory(id.toInt())
    }
}