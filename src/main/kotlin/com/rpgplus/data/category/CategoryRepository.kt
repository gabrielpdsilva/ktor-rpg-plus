package com.rpgplus.data.category

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class CategoryRepository: ICategoryRepository {
    override fun get(id: Int): Category {
        return transaction {
            Categories.select {
                Categories.id eq id
            }.single().let { Categories.toCategory(it) }
        }
    }

    override fun getAll(): List<Category> {
        return transaction {
            Categories.selectAll().map { Categories.toCategory(it) }
        }
    }

    override fun insert(category: Category): Int {
        return transaction {
            Categories.insert {
                it[name] = category.name
            } get Categories.id
        }
    }

    override fun update(category: Category) {
        return transaction {
            Categories.update({ Categories.id eq category.id!!}) {
                it[name] = category.name
            }
        }
    }

    override fun delete(id: Int) {
        return transaction {
            Categories.deleteWhere {
                Categories.id eq id
            }
        }
    }
}