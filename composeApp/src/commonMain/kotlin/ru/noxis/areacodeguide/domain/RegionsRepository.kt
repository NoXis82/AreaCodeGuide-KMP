package ru.noxis.areacodeguide.domain

import kotlinx.coroutines.flow.Flow
import ru.noxis.areacodeguide.core.domain.DataError
import ru.noxis.areacodeguide.core.domain.EmptyResult
import ru.noxis.areacodeguide.core.domain.Result

interface RegionsRepository {

    suspend fun initDb(data: List<Region>): EmptyResult<DataError.Local>

    suspend fun getRegionByCode(code: String): Result<String, DataError>

    fun getRegions(): Flow<List<Region>>
}