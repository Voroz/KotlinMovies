package ferm.jonny.architectureexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ferm.jonny.architectureexample.core.domain.model.ActionResult
import ferm.jonny.architectureexample.features.movies.data.repository.MovieRepository
import ferm.jonny.architectureexample.features.movies.domain.model.FetchResourceError
import ferm.jonny.architectureexample.ui.theme.ComposeTheme
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var movieRepository: MovieRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartScreen()
        }

        lifecycleScope.launch {
            when (val moviesResult = movieRepository.getMovies(1)) {
                is ActionResult.Error -> {
                    when (moviesResult.error.data) {
                        FetchResourceError.NoConnection -> TODO()
                        FetchResourceError.UnAuthorized -> TODO()
                        FetchResourceError.NotFound -> TODO()
                        FetchResourceError.Unknown -> moviesResult.error.message?.let { message -> Log.d("MainActivity", message) }
                    }
                }
                is ActionResult.Success -> {
                    val data = moviesResult.data!!
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun StartScreen() {
    ComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Greeting("Android")
        }
    }
}