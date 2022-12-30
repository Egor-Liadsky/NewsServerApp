package com.lyadskiy.di

import com.lyadskiy.database.dao.users.UserDAO
import com.lyadskiy.database.dao.users.UserDAOImpl
import com.lyadskiy.security.token.TokenService
import com.lyadskiy.security.token.TokenServiceImpl
import org.koin.dsl.module

val userModule = module {
    single<UserDAO> { UserDAOImpl() }
    single<TokenService> { TokenServiceImpl() }
//    single<AuthController> { AuthController(get(), get() }
}