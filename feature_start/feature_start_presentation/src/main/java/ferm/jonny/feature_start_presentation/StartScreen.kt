package ferm.jonny.feature_start_presentation

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun StartScreen(
    navigator: StartScreenNavigator
) {
    Button(onClick = {
        navigator.navigateToMovieOverviews()
    }) {

    }
}