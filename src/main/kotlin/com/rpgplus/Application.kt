package com.rpgplus

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.rpgplus.plugins.*
import com.rpgplus.services.Database
import com.rpgplus.services.Database.configureDatabaseVariables

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDatabaseVariables()
    Database.init()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
