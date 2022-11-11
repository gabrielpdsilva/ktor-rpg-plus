package com.rpgplus.data.category

import kotlinx.serialization.Serializable

@Serializable
data class Category(val name: String, var id: Int? = null)
