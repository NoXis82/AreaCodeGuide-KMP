package ru.noxis.areacodeguide.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import ru.noxis.areacodeguide.presentation.content.SearchRegionContent
import ru.noxis.areacodeguide.presentation.content.SplashContent
import ru.noxis.areacodeguide.presentation.viewmodel.RegionViewModel
import ru.noxis.areacodeguide.presentation.viewmodel.SplashViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Route.RouteGraph) {
            navigation<Route.RouteGraph>(startDestination = Route.SplashContent) {
                composable<Route.SplashContent>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    val viewModel = koinViewModel<SplashViewModel>()
                    SplashContent(viewModel) {
                        navController.popBackStack()
                        navController.navigate(Route.SearchContent)
                    }
                }

                composable<Route.SearchContent>(
                    enterTransition = { slideInHorizontally { initialOffset -> initialOffset } },
                    exitTransition = { slideOutHorizontally { initialOffset -> initialOffset } }
                ) {
                    val viewmodel = koinViewModel<RegionViewModel>()
                    SearchRegionContent(viewmodel)
                }
            }
        }
    }
}