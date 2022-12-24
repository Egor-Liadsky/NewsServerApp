package com.lyadskiy.database.entity

import org.jetbrains.exposed.sql.Table

object CategoriesEntity: Table("categories"){
    val rowId = integer("rowId").autoIncrement()
    val name = varchar("name", 30)

    override val primaryKey = PrimaryKey(rowId, name = "rowId")
}