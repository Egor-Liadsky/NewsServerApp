package com.lyadskiy.plugins

import com.lyadskiy.di.categoriesModule
import com.lyadskiy.di.newsModule
import com.lyadskiy.di.userModule
import io.ktor.server.application.*
import org.koin.core.context.startKoin
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin(){
    install(Koin){
        slf4jLogger()
        modules(categoriesModule, newsModule, userModule)
    }
}