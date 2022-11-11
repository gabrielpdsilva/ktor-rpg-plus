package com.rpgplus.features.generator.name

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.generateName() {
    get("/generator/name") {
        try {
            var generatedName = ""
            transaction {
                val conn = TransactionManager.current().connection
                val query = "SELECT dbo.fn_get_random_name_by_category(1) AS name";
                val statement = conn.prepareStatement(query, false)
                val resultSet = statement.executeQuery()
                if (resultSet.next()) {
                    generatedName = resultSet.getString("name")
                    // TODO why catch doesnt get this line?
                    //generatedName = resultSet.getInt("name").toString()
                }
            }
            call.respond(
                HttpStatusCode.OK,
                generatedName
            )
        } catch (exception: Exception) {
            println("Error: $exception")
            call.respond(
                HttpStatusCode.InternalServerError,
                "${exception.message}"
            )
        }

    }
}