package com.lyadskiy.schemas

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.lyadskiy.dto.CategoriesListDTOResponse
import com.lyadskiy.dto.CategoryDTOReceive
import com.lyadskiy.features.categories.CategoriesController


fun SchemaBuilder.categoriesSchema(categoriesController: CategoriesController){

    type<CategoriesListDTOResponse> {
        description = "All categories"
    }

    query("categories"){
        description = "All categories"
        resolver { -> categoriesController.getCategories() }
    }

    mutation("createCategory"){
        description = "Create category"
        resolver { categoryReceive: CategoryDTOReceive -> categoriesController.createCategory(categoryReceive) }
    }

    mutation("updateCategory"){
        description = "Update category"
        resolver { id: Int, categoryReceive: CategoryDTOReceive -> categoriesController.updateCategories(id, categoryReceive) }
    }

    query("deleteCategory"){
        description = "Delete category"
        resolver { id: Int -> categoriesController.deleteCategory(id) }
    }
}