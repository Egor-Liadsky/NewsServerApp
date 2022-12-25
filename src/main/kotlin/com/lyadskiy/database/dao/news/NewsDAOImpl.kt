package com.lyadskiy.database.dao.news

import com.lyadskiy.database.DatabaseFactory.dbQuery
import com.lyadskiy.database.entity.NewsEntity
import com.lyadskiy.dto.NewsDTOReceive
import com.lyadskiy.dto.NewsFullDTOResponse
import com.lyadskiy.dto.NewsListDTOResponse
import com.lyadskiy.dto.NewsShortDTOResponse
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

class NewsDAOImpl : NewsDAO {
    override suspend fun createNews(categoryId: Int, newsDTOReceive: NewsDTOReceive) = dbQuery {
        NewsEntity.insert {
            it[NewsEntity.categoryId] = categoryId
            it[title] = newsDTOReceive.title
            it[date] = newsDTOReceive.date
            it[shortDescription] = newsDTOReceive.shortDescription
            it[fullDescription] = newsDTOReceive.fullDescription
        }
        return@dbQuery
    }

    override suspend fun getNews(id: Int): NewsFullDTOResponse = dbQuery {
        val newsEntity = NewsEntity.select { NewsEntity.rowId eq id }.single()
        return@dbQuery NewsFullDTOResponse(
            id = id,
            title = newsEntity[NewsEntity.title],
            date = newsEntity[NewsEntity.date],
            shortDescription = newsEntity[NewsEntity.shortDescription],
            fullDescription = newsEntity[NewsEntity.fullDescription]
        )

    }

    override suspend fun getAllNews(page: Int, categoryId: Int): NewsListDTOResponse = dbQuery {
        val limit = 4
        val pageSize: Long = 4
        val skip: Long = (page.toLong() - 1) * pageSize

        val newsEntity = NewsEntity.select { NewsEntity.categoryId eq categoryId }.limit(limit, offset = skip).map {
            NewsShortDTOResponse(
                id = it[NewsEntity.rowId],
                title = it[NewsEntity.title],
                date = it[NewsEntity.date],
                shortDescription = it[NewsEntity.shortDescription]
            )
        }
        println(newsEntity.count())

        return@dbQuery NewsListDTOResponse(
            code = 0,
            list = newsEntity
        )
    }

    override suspend fun updateNews(id: Int, newsDTOReceive: NewsDTOReceive) = dbQuery {
        NewsEntity.update({ NewsEntity.rowId eq id }) {
            it[title] = newsDTOReceive.title
            it[date] = newsDTOReceive.date
            it[shortDescription] = newsDTOReceive.shortDescription
            it[fullDescription] = newsDTOReceive.fullDescription
        }
        return@dbQuery
    }

    override suspend fun deleteNews(id: Int) = dbQuery {
        NewsEntity.deleteWhere { NewsEntity.rowId eq id }
        return@dbQuery
    }
}

val daoNews: NewsDAO = NewsDAOImpl()