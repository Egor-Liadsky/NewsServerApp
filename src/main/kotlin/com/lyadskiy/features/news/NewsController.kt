package com.lyadskiy.features.news

import com.lyadskiy.database.dao.news.daoNews
import com.lyadskiy.dto.NewsDTOReceive
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class NewsController(private val call: ApplicationCall) {

    suspend fun createNews(categoryId: Int) {
        val newsReceive = call.receive<NewsDTOReceive>()
        daoNews.createNews(
            categoryId,
            newsDTOReceive = NewsDTOReceive(
                title = newsReceive.title,
                date = newsReceive.date,
                shortDescription = newsReceive.shortDescription,
                fullDescription = newsReceive.fullDescription
            )
        )
        call.respond(HttpStatusCode.Created, "News created")
    }

    suspend fun getAllNews(categoryId: Int) {
        call.respond(HttpStatusCode.OK, daoNews.getAllNews(categoryId))
    }

    suspend fun getNews(id: Int) {
        call.respond(HttpStatusCode.OK, daoNews.getNews(id))
    }

    suspend fun updateNews(id: Int) {
        val newsReceive = call.receive<NewsDTOReceive>()
        daoNews.updateNews(
            id = id,
            newsDTOReceive = NewsDTOReceive(
                title = newsReceive.title,
                date = newsReceive.date,
                shortDescription = newsReceive.shortDescription,
                fullDescription = newsReceive.fullDescription
            )
        )
        call.respond(HttpStatusCode.OK, "News updated")
    }

    suspend fun deleteNews(id: Int) {
        daoNews.deleteNews(id)
        call.respond(HttpStatusCode.OK, "News deleted")
    }
}