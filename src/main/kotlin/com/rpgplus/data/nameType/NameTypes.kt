package com.rpgplus.data.nameType

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object NameTypes: Table("name_types"){
    val id: Column<Int> = integer("id").autoIncrement()
    val type: Column<String> = varchar("type", 255)
    override val primaryKey = PrimaryKey(id, name = "pk_name_types_id")

    fun toNameType(row: ResultRow): NameType = NameType(
        type = row[type],
        id = row[id],
    )
}