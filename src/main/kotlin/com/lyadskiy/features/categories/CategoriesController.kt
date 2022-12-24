package com.lyadskiy.features.categories

import com.lyadskiy.dto.CategoryDTOReceive
import io.ktor.server.application.*
import io.ktor.server.request.*
import com.lyadskiy.database.dao.categories.daoCategories
import io.ktor.http.*
import io.ktor.server.response.*

class CategoriesController(private val call: ApplicationCall) {

    suspend fun createCategory() {
        val categoryReceive = call.receive<CategoryDTOReceive>()
        daoCategories.createCategory(
            categoryDTOReceive = CategoryDTOReceive(
                name = categoryReceive.name
            )
        )
        call.respond(HttpStatusCode.Created, "Category created")
    }

    suspend fun getCategories() {
        call.respond(HttpStatusCode.OK, daoCategories.getCategories())
    }

    suspend fun updateCategories(id: Int){
        val categoryReceive = call.receive<CategoryDTOReceive>()
        daoCategories.updateCategory(id, categoryReceive)
        call.respond(HttpStatusCode.OK, "Category updated")
    }

    suspend fun deleteCategory(id: Int){
        daoCategories.deleteCategory(id)
        call.respond(HttpStatusCode.OK, "Category deleted")
    }
}