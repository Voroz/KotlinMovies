package ferm.jonny.architectureexample.navigation

import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.DestinationSpec
import ferm.jonny.feature_movies_presentation.destinations.MovieDetailsScreenDestination
import ferm.jonny.feature_movies_presentation.destinations.MovieOverviewScreenDestination
import ferm.jonny.feature_movies_presentation.movie_detail.MovieDetailsScreenNavigator
import ferm.jonny.feature_movies_presentation.movies_overview.MoviesOverviewScreenNavigator
import ferm.jonny.feature_start_presentation.StartScreenNavigator

class Navigator(
    private val currentDestination: DestinationSpec<*>,
    private val navController: NavController
) : StartScreenNavigator, MoviesOverviewScreenNavigator, MovieDetailsScreenNavigator {

    override fun navigateToDetails(movieId: Int) {
        navController.navigateTo(MovieDetailsScreenDestination(movieId))
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateToMovieOverviews() {
        navController.navigateTo(MovieOverviewScreenDestination())
    }
}