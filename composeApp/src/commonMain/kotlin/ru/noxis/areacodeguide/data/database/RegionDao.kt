package ru.noxis.areacodeguide.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RegionDao {

    @Upsert
    suspend fun upsert(regions: List<RegionEntity>)

    @Query("SELECT * FROM RegionEntity")
    fun getRegions(): Flow<List<RegionEntity>>

    @Query("SELECT * FROM RegionEntity WHERE code = :code")
    suspend fun getRegionByCode(code: String): RegionEntity?

}