package com.mukhaled.englishpremierleagueapp.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mukhaled.englishpremierleagueapp.core.presentation.theme.EnglishPremierLeagueAppTheme
import com.mukhaled.englishpremierleagueapp.premierLeagueMatches.presentation.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.Aqua_waterfliter.tech_app.core.ui.uiComponents.ComposableLifecycle
import com.mukhaled.englishpremierleagueapp.premierLeagueMatches.presentation.TodayMatchScreen
import com.mukhaled.englishpremierleagueapp.premierLeagueMatches.presentation.UiEvent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<ViewModel>()
            ComposableLifecycle { source, event ->
                if (event == Lifecycle.Event.ON_START) {
                    viewModel.onEvent(UiEvent.GetInitialData)
                }
            }
            EnglishPremierLeagueAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodayMatchScreen(viewModel.state.collectAsState().value)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EnglishPremierLeagueAppTheme {
        Greeting("Android")
    }
}