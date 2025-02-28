package ru.noxis.areacodeguide.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object RegionsDatabaseConstructor: RoomDatabaseConstructor<RegionsDatabase> {
    override fun initialize(): RegionsDatabase
}