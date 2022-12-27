package com.lyadskiy.database.entity

import org.jetbrains.exposed.sql.Table

object UsersEntity : Table("users") {
    val rowId = integer("id_user").autoIncrement()
    val login = varchar("login", 30)
    val password = varchar("password", 80)
    val salt = varchar("salt", 100)

    override val primaryKey = PrimaryKey(rowId, name = "id_user")
}