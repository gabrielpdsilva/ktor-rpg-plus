package com.rpgplus.plugins

import com.rpgplus.services.Database

fun connectDatabase() {
    Database.init()
}