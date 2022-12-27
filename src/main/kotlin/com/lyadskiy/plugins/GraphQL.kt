package com.lyadskiy.plugins

import com.apurebase.kgraphql.GraphQL
import com.lyadskiy.features.categories.CategoriesController
import com.lyadskiy.features.news.NewsController
import com.lyadskiy.schemas.categoriesSchema
import com.lyadskiy.schemas.newsSchema
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureGraphQL() {
    val newsController by inject<NewsController>()
    val categoriesController by inject<CategoriesController>()

    install(GraphQL) {
        playground = true
        schema {
            newsSchema(newsController)
            categoriesSchema(categoriesController)
        }
    }
}