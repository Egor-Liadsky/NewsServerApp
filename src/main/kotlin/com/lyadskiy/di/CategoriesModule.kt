package com.lyadskiy.di

import com.lyadskiy.database.dao.categories.CategoriesDAO
import com.lyadskiy.database.dao.categories.CategoriesDAOImpl
import com.lyadskiy.features.categories.CategoriesController
import org.koin.dsl.module

val categoriesModule = module {
    single<CategoriesDAO> { CategoriesDAOImpl() }
    single<CategoriesController> { CategoriesController(get()) }
}