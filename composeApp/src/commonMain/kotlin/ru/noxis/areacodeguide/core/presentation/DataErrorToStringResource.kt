package ru.noxis.areacodeguide.core.presentation

import areacodeguide.composeapp.generated.resources.Res
import areacodeguide.composeapp.generated.resources.error_disk_full
import areacodeguide.composeapp.generated.resources.error_unknown
import ru.noxis.areacodeguide.core.domain.DataError

fun DataError.toUiText(): UiText {
    val stringRes = when (this) {
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_unknown
    }

    return UiText.StringResourceId(stringRes)
}