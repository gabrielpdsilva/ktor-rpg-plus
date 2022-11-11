package com.rpgplus.data.name

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val id: Int,
    val name: String,
    val categoryId: Int
)