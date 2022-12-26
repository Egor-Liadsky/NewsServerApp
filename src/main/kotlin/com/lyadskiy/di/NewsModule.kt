package com.lyadskiy.di

import com.lyadskiy.database.dao.news.NewsDAO
import com.lyadskiy.database.dao.news.NewsDAOImpl
import com.lyadskiy.features.news.NewsController
import org.koin.dsl.module

val newsModule = module {
    single<NewsDAO> { NewsDAOImpl() }
    single<NewsController> { NewsController(get()) }
}