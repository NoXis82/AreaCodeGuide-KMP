package ru.noxis.areacodeguide.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RegionEntity::class],
    version = 1
)
@ConstructedBy(RegionsDatabaseConstructor::class)
abstract class RegionsDatabase : RoomDatabase() {
    abstract val regionDao: RegionDao

    companion object {
        const val DB_NAME = "regions.db"
    }
}
