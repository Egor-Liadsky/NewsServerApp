package com.lyadskiy.schemas

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.lyadskiy.dto.NewsDTOReceive
import com.lyadskiy.dto.NewsListDTOResponse
import com.lyadskiy.features.news.NewsController

fun SchemaBuilder.newsSchema(newsController: NewsController) {

    type<NewsListDTOResponse> {
        description = "All news"
    }

    query("newsAll") {
        description = "All news"
        resolver { categoryId: Int, page: Int -> newsController.getAllNews(page, categoryId) }
    }

    query("news") {
        description = "Get news"
        resolver { id: Int -> newsController.getNews(id) }
    }

    inputType<NewsDTOReceive> {
        name = "newsInput"
    }

    mutation("createNews") {
        description = "Create news"
        resolver { categoryId: Int, newsReceive: NewsDTOReceive -> newsController.createNews(categoryId, newsReceive) }
    }

    mutation("updateNews") {
        description = "Update news"
        resolver { id: Int, newsReceive: NewsDTOReceive -> newsController.updateNews(id, newsReceive) }
    }

    query("deleteNews") {
        description = "deleteNews"
        resolver { id: Int -> newsController.deleteNews(id) }
    }
}