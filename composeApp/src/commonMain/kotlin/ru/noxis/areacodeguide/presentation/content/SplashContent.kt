package ru.noxis.areacodeguide.presentation.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import areacodeguide.composeapp.generated.resources.Res
import areacodeguide.composeapp.generated.resources.flag_russia
import areacodeguide.composeapp.generated.resources.ic_check_circle_ok
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import ru.noxis.areacodeguide.presentation.state.SplashState
import ru.noxis.areacodeguide.presentation.viewmodel.SplashViewModel

@Composable
fun SplashContent(
    viewModel: SplashViewModel = koinViewModel(),
    navigateToSearchContent: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SplashBox(state = { state }) {
        navigateToSearchContent.invoke()
    }
}

@Composable
internal fun SplashBox(
    state: () -> SplashState,
    navigateToSearchContent: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(Res.drawable.flag_russia),
                contentDescription = ""
            )
        }
        Spacer(Modifier.height(64.dp))
        Column(
            modifier = Modifier.padding(vertical = 32.dp).weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            when {
                state().isLoading -> CircularProgressIndicator()
                state().error != null -> {
                    Text(
                        state().error!!.asString(),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                state().navigateToSearchContent -> {
                    Image(
                        modifier = Modifier.size(64.dp),
                        painter = painterResource(Res.drawable.ic_check_circle_ok),
                        contentDescription = null
                    )
                    navigateToSearchContent.invoke()
                }
            }

            Text(
                "Версия:1.0.0",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
