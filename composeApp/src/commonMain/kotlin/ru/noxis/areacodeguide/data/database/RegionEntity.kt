package ru.noxis.areacodeguide.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RegionEntity(
    @PrimaryKey(autoGenerate = false) val code: String,
    val nameRegion: String
)
