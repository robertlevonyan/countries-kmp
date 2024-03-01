package com.robertlevonyan.countrieskmp
interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
