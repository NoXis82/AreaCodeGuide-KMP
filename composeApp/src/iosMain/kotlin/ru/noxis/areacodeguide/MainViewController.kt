package ru.noxis.areacodeguide

import androidx.compose.ui.window.ComposeUIViewController
import ru.noxis.areacodeguide.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }