package ru.noxis.areacodeguide.presentation.content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import areacodeguide.composeapp.generated.resources.Res
import areacodeguide.composeapp.generated.resources.flag_russia
import areacodeguide.composeapp.generated.resources.not_found
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ru.noxis.areacodeguide.presentation.action.RegionAction
import ru.noxis.areacodeguide.presentation.state.RegionState
import ru.noxis.areacodeguide.presentation.viewmodel.RegionViewModel

@Composable
fun SearchRegionContent(
    viewModel: RegionViewModel = koinViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchRegionBox(
        state = { state },
        onAction = { action ->
            when (action) {
                is RegionAction.OnSearchQueryChange -> {
                    if (action.query.length <= 3) {
                        viewModel.onAction(action)
                    }
                }
            }
        },
        onImeSearch = {
            keyboardController?.hide()
        }
    )

}

@Composable
internal fun SearchRegionBox(
    state: () -> RegionState,
    onAction: (RegionAction) -> Unit,
    onImeSearch: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(32.dp)
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            state().errorMessage != null -> {
                Text(
                    text = state().errorMessage!!.asString(),
                    style = TextStyle(
                        fontSize = 28.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                )
            }

            state().searchResults.isNotEmpty() -> {
                Text(
                    text = state().searchResults,
                    style = TextStyle(
                        fontSize = 28.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                )
            }

            state().searchResults.isEmpty() -> {
                Text(
                    text = stringResource(Res.string.not_found),
                    style = TextStyle(
                        fontSize = 28.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
        Surface(
            modifier = Modifier.fillMaxWidth().padding(64.dp),
            shape = RoundedCornerShape(32.dp),
            border = BorderStroke(width = 6.dp, color = Color.White)
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                shape = RoundedCornerShape(32.dp),
                border = BorderStroke(width = 16.dp, color = Color.Black)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth().height(IntrinsicSize.Min),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = state().searchQuery,
                        onValueChange = {
                            onAction(RegionAction.OnSearchQueryChange(it))
                        },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            cursorColor = Color.Black,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                onImeSearch()
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Search
                        ),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        leadingIcon = {},
                        trailingIcon = {},
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("RUS", fontSize = 32.sp, fontWeight = FontWeight.Light)
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(
                            painter = painterResource(Res.drawable.flag_russia),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}