package ru.noxis.areacodeguide.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.noxis.areacodeguide.core.domain.DataError
import ru.noxis.areacodeguide.core.domain.EmptyResult
import ru.noxis.areacodeguide.core.domain.Result
import ru.noxis.areacodeguide.data.database.RegionDao
import ru.noxis.areacodeguide.data.mappers.toRegion
import ru.noxis.areacodeguide.data.mappers.toRegionEntity
import ru.noxis.areacodeguide.domain.Region
import ru.noxis.areacodeguide.domain.RegionsRepository

class DefaultRegionRepository(
    private val regionDao: RegionDao
) : RegionsRepository {

    override suspend fun initDb(data: List<Region>): EmptyResult<DataError.Local> {
        return try {
            val regionsEntityList = data.map { region ->
                region.toRegionEntity()
            }
            regionDao.upsert(regions = regionsEntityList)
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun getRegionByCode(code: String): Result<String, DataError.Local> {
        return try {
            val localResult = regionDao.getRegionByCode(code)?.toRegion()?.nameRegion ?: ""
            Result.Success(localResult)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }


    override fun getRegions(): Flow<List<Region>> {
        return regionDao.getRegions().map { regionsEntity ->
            regionsEntity.map { it.toRegion() }
        }
    }
}