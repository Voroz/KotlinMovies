package ferm.jonny.architectureexample.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import ferm.jonny.feature_movies.presentation.MoviesNavGraph
import ferm.jonny.feature_start.StartNavGraph

@ExperimentalComposeUiApi
object RootNavGraph: NavGraphSpec {

    override val route = "root"

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

    override val startRoute = StartNavGraph

    override val nestedNavGraphs = listOf(
        StartNavGraph,
        MoviesNavGraph
    )
}