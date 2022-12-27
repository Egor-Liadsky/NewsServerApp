package com.lyadskiy.features.categories

import com.lyadskiy.database.dao.categories.CategoriesDAO
import com.lyadskiy.dto.CategoriesListDTOResponse
import com.lyadskiy.dto.CategoryDTOReceive

class CategoriesController(private val categoriesDAO: CategoriesDAO) {

    suspend fun createCategory(categoryDTOReceive: CategoryDTOReceive): String {
        categoriesDAO.createCategory(
            categoryDTOReceive = CategoryDTOReceive(
                name = categoryDTOReceive.name
            )
        )
        return "Category created"
    }

    suspend fun getCategories(): CategoriesListDTOResponse {
        return categoriesDAO.getCategories()
    }

    suspend fun updateCategories(id: Int, categoryDTOReceive: CategoryDTOReceive): String {
        categoriesDAO.updateCategory(id, categoryDTOReceive)
        return "Category updated"
    }

    suspend fun deleteCategory(id: Int): String {
        categoriesDAO.deleteCategory(id)
        return "Category deleted"
    }
}