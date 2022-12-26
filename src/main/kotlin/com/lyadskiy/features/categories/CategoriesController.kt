package com.lyadskiy.features.categories

import com.lyadskiy.database.dao.categories.CategoriesDAO
import com.lyadskiy.dto.CategoriesListDTOResponse
import com.lyadskiy.dto.CategoryDTOReceive

class CategoriesController(private val categoriesDAO: CategoriesDAO) {

    suspend fun createCategory(categoryDTOReceive: CategoryDTOReceive) {
        categoriesDAO.createCategory(
            categoryDTOReceive = CategoryDTOReceive(
                name = categoryDTOReceive.name
            )
        )
    }

    suspend fun getCategories(): CategoriesListDTOResponse {
        return categoriesDAO.getCategories()
    }

    suspend fun updateCategories(id: Int, categoryDTOReceive: CategoryDTOReceive) {
        categoriesDAO.updateCategory(id, categoryDTOReceive)
    }

    suspend fun deleteCategory(id: Int) {
        categoriesDAO.deleteCategory(id)
    }
}