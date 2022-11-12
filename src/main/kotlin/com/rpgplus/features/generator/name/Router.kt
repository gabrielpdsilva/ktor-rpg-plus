package com.rpgplus.features.generator.name

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.generateFullName() {
    get("/generator/full-name/{categoryId}") {
        val categoryId = call.parameters["categoryId"]
        try {
            var generatedName: String? = ""
            transaction {
                val conn = TransactionManager.current().connection
                val query = "SELECT dbo.fn_get_full_random_name_by_category($categoryId) AS full_name";
                val statement = conn.prepareStatement(query, false)
                val resultSet = statement.executeQuery()
                if (resultSet.next()) {
                    generatedName = resultSet.getString("full_name")
                }
            }
            val message = generatedName ?: "No names registered for the specified category."
            call.respond(
                HttpStatusCode.OK,
                message
            )
        } catch (exception: Exception) {
            println("Error: $exception.message")
            call.respond(
                HttpStatusCode.InternalServerError,
                "${exception.message}"
            )
        }

    }
}

fun Route.generateName() {
    get("/generator/name/{categoryId}") {
        val categoryId = call.parameters["categoryId"]
        try {
            var generatedName: String? = ""
            transaction {
                val conn = TransactionManager.current().connection
                val query = "SELECT dbo.fn_get_random_name_by_category($categoryId) AS name";
                val statement = conn.prepareStatement(query, false)
                val resultSet = statement.executeQuery()
                if (resultSet.next()) {
                    generatedName = resultSet.getString("name")
                }
            }
            val message = generatedName ?: "No names registered for the specified category."
            call.respond(
                HttpStatusCode.OK,
                message
            )
        } catch (exception: Exception) {
            println("Error: $exception.message")
            call.respond(
                HttpStatusCode.InternalServerError,
                "${exception.message}"
            )
        }

    }
}