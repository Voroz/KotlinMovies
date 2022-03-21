package ferm.jonny.architectureexample.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.DestinationSpec
import ferm.jonny.feature_movies.presentation.destinations.MovieDetailsScreenDestination
import ferm.jonny.feature_movies.presentation.movies_overview.MoviesOverviewScreenNavigator

@ExperimentalComposeUiApi
class MoviesOverviewScreenNavigatorImpl(
    private val currentDestination: DestinationSpec<*>,
    private val navController: NavController
) : MoviesOverviewScreenNavigator {
    override fun navigateToDetails(movieId: Int) {
        navController.navigateTo(MovieDetailsScreenDestination(movieId))
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}