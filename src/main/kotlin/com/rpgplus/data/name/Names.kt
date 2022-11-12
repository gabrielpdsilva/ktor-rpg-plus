package com.rpgplus.data.name

import com.rpgplus.data.category.Categories
import com.rpgplus.data.nameType.NameTypes
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Names: Table(){
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)
    override val primaryKey = PrimaryKey(id, name = "pk_names_id")
    val categoryId: Column<Int> = integer("category_id").references(Categories.id)
    val typeId: Column<Int> = integer("type_id").references(NameTypes.id)

    fun toName(row: ResultRow): Name = Name(
        name = row[name],
        categoryId = row[categoryId],
        typeId = row[typeId],
        id = row[id],
    )
}