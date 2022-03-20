package ferm.jonny.architectureexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import ferm.jonny.architectureexample.features.movies.data.repository.MovieRepository
import ferm.jonny.architectureexample.features.movies.presentation.NavGraphs
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
    }
}