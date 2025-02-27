package ru.noxis.areacodeguide

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform