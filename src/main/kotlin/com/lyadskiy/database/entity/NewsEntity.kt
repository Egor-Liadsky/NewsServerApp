package com.lyadskiy.database.entity

import org.jetbrains.exposed.sql.Table

object NewsEntity : Table("news") {
    val rowId = integer("row_id").autoIncrement()
    val categoryId = integer("category_id")
    val title = varchar("title", 100)
    val date = varchar("date", 50)
    val shortDescription = varchar("short_description", 400)
    val fullDescription = varchar("full_description", 1200)

    override val primaryKey = PrimaryKey(rowId, name = "row_id")
}