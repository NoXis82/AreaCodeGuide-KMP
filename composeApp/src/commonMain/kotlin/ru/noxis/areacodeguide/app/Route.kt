package ru.noxis.areacodeguide.app

import kotlinx.serialization.Serializable


sealed interface Route {

    @Serializable
    data object RouteGraph: Route

    @Serializable
    data object SplashContent: Route

    @Serializable
    data object SearchContent: Route
}