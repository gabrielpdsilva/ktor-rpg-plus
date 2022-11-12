package com.rpgplus.services

import com.rpgplus.data.category.Categories
import com.rpgplus.data.name.Names
import com.rpgplus.data.nameType.NameTypes
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object Database {

    private var url = ""
    private var driver = ""
    private var user = ""
    private var password = ""

    fun Application.configureDatabaseVariables() {
        url = System.getenv("db_url")?.toString() ?: ""
        driver = System.getenv("db_driver")?.toString() ?: ""
        user = System.getenv("db_user")?.toString() ?: ""
        password = System.getenv("db_password")?.toString() ?: ""
    }

    private fun createTables() {
        transaction {
            SchemaUtils.create(Categories)
        }
        transaction {
            SchemaUtils.create(NameTypes)
        }
        transaction {
            SchemaUtils.create(Names)
        }
    }

    fun init() {
        try {
            Database.connect(url, driver, user, password)
            createTables()
            println("Connected to database!")
        } catch (e: Exception) {
            println("Error connecting to database: $e")
        }
    }
}