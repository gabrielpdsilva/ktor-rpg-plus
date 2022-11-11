package com.rpgplus.data.name

import com.rpgplus.data.category.Categories
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Names: Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)
    override val primaryKey = PrimaryKey(id, name = "pk_names_id")
    val categoryId: Column<Int> = integer("category_id").references(Categories.id)

    fun toName(row: ResultRow): Name = Name(
        id = row[id],
        name = row[name],
        categoryId = row[categoryId]
    )
}