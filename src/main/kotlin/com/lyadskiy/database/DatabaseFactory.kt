package com.lyadskiy.database

import com.lyadskiy.database.entity.CategoriesEntity
import com.lyadskiy.database.entity.NewsEntity
import com.lyadskiy.database.entity.UsersEntity
import com.lyadskiy.utils.Constants
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init(){
        val driverName = "org.postgresql.Driver"
        val jdbcUrl = "jdbc:postgresql://localhost:5432/${Constants.DATABASE_NAME}"
        Database.connect(jdbcUrl, driverName, user = Constants.USER_DATABASE, password = Constants.PASSWORD_DATABASE)

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(CategoriesEntity, NewsEntity, UsersEntity)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction { block() }
}