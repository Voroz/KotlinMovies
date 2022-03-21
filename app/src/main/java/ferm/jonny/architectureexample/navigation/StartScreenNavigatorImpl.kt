package ferm.jonny.architectureexample.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.DestinationSpec
import ferm.jonny.feature_movies.presentation.destinations.MovieOverviewScreenDestination
import ferm.jonny.feature_start.StartScreenNavigator

@ExperimentalComposeUiApi
class StartScreenNavigatorImpl(
    private val currentDestination: DestinationSpec<*>,
    private val navController: NavController
) : StartScreenNavigator {
    override fun navigateToMovieOverviews() {
        navController.navigateTo(MovieOverviewScreenDestination())
    }
}