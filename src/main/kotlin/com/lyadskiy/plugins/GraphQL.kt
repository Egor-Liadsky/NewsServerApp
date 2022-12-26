package com.lyadskiy.plugins

import com.apurebase.kgraphql.GraphQL
import com.lyadskiy.database.dao.categories.CategoriesDAOImpl
import com.lyadskiy.database.dao.news.NewsDAOImpl
import com.lyadskiy.dto.NewsDTOReceive
import com.lyadskiy.dto.NewsFullDTOResponse
import com.lyadskiy.dto.NewsListDTOResponse
import com.lyadskiy.features.news.NewsController
import io.ktor.server.application.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureGraphQL() {
    val newsController by inject<NewsController>()
    install(GraphQL) {
        playground = true
        schema {
            type<NewsListDTOResponse>() {
                description = "News DTO"
            }

            query("news") {
                description = "News full info"
                resolver { categoryId: Int, page: Int -> newsController.getAllNews(page, categoryId) }
            }

            inputType<NewsDTOReceive>(){
                name = "newsInput"
            }

            mutation("addNews"){
                description = "Create news"
                resolver {categoryId: Int, newsReceive: NewsDTOReceive -> newsController.createNews(categoryId, newsReceive) }
            }
        }
    }
}