package com.lyadskiy.database.dao.news

import com.lyadskiy.dto.NewsDTOReceive
import com.lyadskiy.dto.NewsFullDTOResponse
import com.lyadskiy.dto.NewsListDTOResponse

interface NewsDAO {

    suspend fun createNews(categoryId: Int, newsDTOReceive: NewsDTOReceive)

    suspend fun getNews(id: Int): NewsFullDTOResponse

    suspend fun getAllNews(categoryId: Int, page: Int, pageSize: Int): NewsListDTOResponse

    suspend fun updateNews(id: Int, newsDTOReceive: NewsDTOReceive)

    suspend fun deleteNews(id: Int)

}