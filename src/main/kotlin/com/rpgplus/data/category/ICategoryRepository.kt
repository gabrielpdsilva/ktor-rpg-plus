package com.rpgplus.data.category

interface ICategoryRepository {
    fun get(id: Int): Category
    fun getAll(): List<Category>
    fun insert(category: Category): Int
    fun update(category: Category)
    fun delete(id: Int)
}
