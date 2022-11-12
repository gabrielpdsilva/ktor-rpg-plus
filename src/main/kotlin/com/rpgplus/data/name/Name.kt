package com.rpgplus.data.name

import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val name: String,
    val categoryId: Int,
    val typeId: Int,
    var id: Int?,
)