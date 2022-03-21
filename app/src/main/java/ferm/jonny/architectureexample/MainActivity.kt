package ferm.jonny.architectureexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.spec.NavGraphSpec
import dagger.hilt.android.AndroidEntryPoint
import ferm.jonny.architectureexample.navigation.MovieDetailsScreenNavigatorImpl
import ferm.jonny.architectureexample.navigation.MoviesOverviewScreenNavigatorImpl
import ferm.jonny.architectureexample.navigation.RootNavGraph
import ferm.jonny.architectureexample.navigation.StartScreenNavigatorImpl
import ferm.jonny.feature_start.StartNavGraph

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            DestinationsNavHost(
                navController = navController,
                navGraph = RootNavGraph,
                startRoute = StartNavGraph,
                dependenciesContainerBuilder = {
                    dependency(StartScreenNavigatorImpl(destination, navController))
                    dependency(MoviesOverviewScreenNavigatorImpl(destination, navController))
                    dependency(MovieDetailsScreenNavigatorImpl(destination, navController))
                }
            )
        }
    }
}