package com.rpgplus.features.category

import com.rpgplus.data.category.Category
import com.rpgplus.data.category.CategoryRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.SQLException

// TODO test
fun Route.addCategory() {
    post("/category") {
        // TODO use values from body
        try {
            val category = Category("Random category")
            val categoryRepository = CategoryRepository()
            categoryRepository.insert(category)
            call.respond(
                HttpStatusCode.OK,
                "Category has been added successfully!"
            )
        } catch (exception: SQLException) {
            println("The following exception has occurred: $exception")
            call.respond(
                HttpStatusCode.InternalServerError,
                "${exception.message}"
            )
        }
    }
}

fun Route.getCategory() {
    get("/category/{categoryId}") {
        val categoryId = call.parameters["categoryId"]?.toIntOrNull()
        if (categoryId == null) {
            call.respond(
                HttpStatusCode.UnprocessableEntity,
                "Category id must be a valid number"
            )
        }
        try {
            val categoryRepository = CategoryRepository()
            val category = categoryRepository.get(categoryId!!)
            call.respond(
                HttpStatusCode.OK,
                category
            )
        } catch (exception: SQLException) {
            call.respond(
                HttpStatusCode.InternalServerError,
                "${exception.message}"
            )
        }
    }
}

fun Route.getCategories() {
    get("/categories") {
        val categoryRepository = CategoryRepository()
        try {
            call.respond(
                HttpStatusCode.OK,
                categoryRepository.getAll()
            )
        } catch (exception: SQLException) {
            call.respond(
                HttpStatusCode.InternalServerError,
                "${exception.message}"
            )
        }
    }
}

// TODO test
fun Route.updateCategory() {
    put("/category/{categoryId}") {
        val categoryId = call.parameters["categoryId"]?.toIntOrNull()
        if (categoryId == null) {
            call.respond(
                HttpStatusCode.InternalServerError,
                "ERROOOOOOOOU"
            )
        }
        val categoryRepository = CategoryRepository()
        val category = Category("Updated medieval", 1)
        categoryRepository.update(category)
        try {
            call.respond(
                HttpStatusCode.OK,
                "Sucesso"
            )
        } catch (exception: SQLException) {
            call.respond(
                HttpStatusCode.InternalServerError,
                "${exception.message}"
            )
        }
    }
}
// TODO test
fun Route.deleteCategory() {
    delete("/category/{categoryId}") {
        val categoryId = call.parameters["categoryId"]?.toIntOrNull()
        if (categoryId == null) {
            call.respond(
                HttpStatusCode.InternalServerError,
                "ERROOOOOOOOU"
            )
        }
        val categoryRepository = CategoryRepository()
        categoryRepository.delete(categoryId!!)
        try {
            call.respond(
                HttpStatusCode.OK,
                "Sucesso"
            )
        } catch (exception: SQLException) {
            call.respond(
                HttpStatusCode.InternalServerError,
                "${exception.message}"
            )
        }
    }
}