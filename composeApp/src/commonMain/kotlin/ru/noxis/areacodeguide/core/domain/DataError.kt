package ru.noxis.areacodeguide.core.domain

sealed interface DataError : Error {

    enum class Local : DataError {
        DISK_FULL,
        UNKNOWN
    }
}