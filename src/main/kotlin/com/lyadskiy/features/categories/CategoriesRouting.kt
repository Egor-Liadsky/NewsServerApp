package com.lyadskiy.features.categories

import com.lyadskiy.dto.CategoryDTOReceive
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.categoriesRouting() {

    val categoriesController by inject<CategoriesController>()

    get("/v1/news/categories") {
        call.respond(HttpStatusCode.OK, categoriesController.getCategories())
    }

    authenticate("auth-jwt") {

        post("/v1/news/categories") {
            val categoryReceive = call.receive<CategoryDTOReceive>()
            categoriesController.createCategory(categoryReceive)
            call.respond(HttpStatusCode.Created, "Category created")
        }

        put("/v1/news/categories/{id?}") {
            val categoryReceive = call.receive<CategoryDTOReceive>()
            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.NotFound, "Missing id")
            categoriesController.updateCategories(id.toInt(), categoryReceive)
            call.respond(HttpStatusCode.OK, "Category updated")
        }

        delete("/v1/news/categories/{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.NotFound, "Missing id")
            categoriesController.deleteCategory(id.toInt())
            call.respond(HttpStatusCode.OK, "Category deleted")
        }
    }
}