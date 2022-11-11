package com.rpgplus.data.category

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Categories: Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)
    override val primaryKey = PrimaryKey(id, name = "pk_categories_id")

    fun toCategory(row: ResultRow): Category = Category(
        id = row[id],
        name = row[name],
    )
}