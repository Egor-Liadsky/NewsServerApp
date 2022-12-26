package com.lyadskiy.features.news

import com.lyadskiy.database.dao.news.NewsDAO
import com.lyadskiy.dto.NewsDTOReceive
import com.lyadskiy.dto.NewsFullDTOResponse
import com.lyadskiy.dto.NewsListDTOResponse

class NewsController(private val newsDAO: NewsDAO) {

    suspend fun createNews(categoryId: Int, newsReceive: NewsDTOReceive) {
        newsDAO.createNews(
            categoryId,
            newsDTOReceive = NewsDTOReceive(
                title = newsReceive.title,
                date = newsReceive.date,
                shortDescription = newsReceive.shortDescription,
                fullDescription = newsReceive.fullDescription
            )
        )
    }

    suspend fun getAllNews(page: Int, categoryId: Int): NewsListDTOResponse {
        return newsDAO.getAllNews(categoryId, page, pageSize = 4)
    }

    suspend fun getNews(id: Int): NewsFullDTOResponse {
        return newsDAO.getNews(id)
    }

    suspend fun updateNews(id: Int, newsReceive: NewsDTOReceive) {
        newsDAO.updateNews(
            id = id,
            newsDTOReceive = NewsDTOReceive(
                title = newsReceive.title,
                date = newsReceive.date,
                shortDescription = newsReceive.shortDescription,
                fullDescription = newsReceive.fullDescription
            )
        )
    }

    suspend fun deleteNews(id: Int) {
        newsDAO.deleteNews(id)
    }
}