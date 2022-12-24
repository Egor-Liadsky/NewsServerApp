package com.lyadskiy.database.dao.categories

import com.lyadskiy.dto.CategoriesListDTOResponse
import com.lyadskiy.dto.CategoryDTOReceive
import com.lyadskiy.dto.CategoryDTOResponse

interface CategoriesDAO {

    suspend fun createCategory(categoryDTOReceive: CategoryDTOReceive)

    suspend fun getCategories(): CategoriesListDTOResponse

    suspend fun updateCategory(id: Int, categoryDTOReceive: CategoryDTOReceive)

    suspend fun deleteCategory(id: Int)
}