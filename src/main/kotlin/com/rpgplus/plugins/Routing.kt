package com.rpgplus.plugins

import com.rpgplus.features.category.getCategories
import com.rpgplus.features.category.getCategory
import com.rpgplus.features.generator.name.generateFullName
import com.rpgplus.features.generator.name.generateName
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureRouting() {

    fun categoryRoutes() {
       routing {
           getCategory()
           getCategories()
       }
    }

    fun nameGeneratorRoutes() {
        routing {
            generateFullName()
            generateName()
        }
    }

    routing {

        nameGeneratorRoutes()
        categoryRoutes()

        get("/") {
            call.respondText("Hello World!")
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
