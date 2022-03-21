package ferm.jonny.feature_start

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

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