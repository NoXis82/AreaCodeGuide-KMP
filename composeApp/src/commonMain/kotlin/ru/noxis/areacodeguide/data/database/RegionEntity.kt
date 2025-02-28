package ru.noxis.areacodeguide.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RegionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val code: String,
    val nameRegion: String
)
