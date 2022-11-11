package com.rpgplus.services

import com.rpgplus.data.category.Categories
import com.rpgplus.data.name.Names
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.selectAll
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

    private fun testsDB() {
        transaction {
            SchemaUtils.create(Categories)
        }
        transaction {
            SchemaUtils.create(Names)
        }
        val categories = transaction {
            Categories.selectAll().map { Categories.toCategory(it) }
        }
        val names = transaction {
            Names.selectAll().map { Names.toName(it) }
        }
        println("categories: $categories")
        println("names: $names")
    }

    fun init() {
        try {
            Database.connect(url, driver, user, password)
            println("Connected to database!")
        } catch (e: Exception) {
            println("Error connecting to database: $e")
        }
    }
}