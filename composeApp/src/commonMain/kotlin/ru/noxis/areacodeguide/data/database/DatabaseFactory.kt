package ru.noxis.areacodeguide.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<RegionsDatabase>
}
