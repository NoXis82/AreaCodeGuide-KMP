package ru.noxis.areacodeguide.data.mappers

import ru.noxis.areacodeguide.data.database.RegionEntity
import ru.noxis.areacodeguide.domain.Region

fun RegionEntity.toRegion(): Region {
    return Region(
        code = this.code,
        nameRegion = this.nameRegion
    )
}

fun Region.toRegionEntity(): RegionEntity {
    return RegionEntity(
        code = this.code,
        nameRegion = this.nameRegion
    )
}