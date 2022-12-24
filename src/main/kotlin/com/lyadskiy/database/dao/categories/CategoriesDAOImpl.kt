package com.lyadskiy.database.dao.categories

import com.lyadskiy.database.DatabaseFactory.dbQuery
import com.lyadskiy.database.entity.CategoriesEntity
import com.lyadskiy.database.entity.CategoriesEntity.name
import com.lyadskiy.database.entity.CategoriesEntity.rowId
import com.lyadskiy.dto.CategoriesListDTOResponse
import com.lyadskiy.dto.CategoryDTOReceive
import com.lyadskiy.dto.CategoryDTOResponse
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class CategoriesDAOImpl : CategoriesDAO {
    override suspend fun createCategory(categoryDTOReceive: CategoryDTOReceive) = dbQuery {
        CategoriesEntity.insert {
            it[name] = categoryDTOReceive.name
        }
        return@dbQuery
    }


    override suspend fun getCategories(): CategoriesListDTOResponse = dbQuery {
        val categoriesEntity = CategoriesEntity.selectAll().map {
            CategoryDTOResponse(
                id = it[rowId],
                name = it[name]
            )
        }
        return@dbQuery CategoriesListDTOResponse(
            code = 0,
            list = categoriesEntity
        )
    }

    override suspend fun updateCategory(id: Int, categoryDTOReceive: CategoryDTOReceive) = dbQuery {
        CategoriesEntity.update({ rowId eq id }) {
            it[name] = categoryDTOReceive.name
        }
        return@dbQuery
    }

    override suspend fun deleteCategory(id: Int) = dbQuery {
        CategoriesEntity.deleteWhere { rowId eq id }
        return@dbQuery
    }
}

val daoCategories: CategoriesDAO = CategoriesDAOImpl()