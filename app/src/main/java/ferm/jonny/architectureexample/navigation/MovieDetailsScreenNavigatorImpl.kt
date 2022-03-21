package ferm.jonny.architectureexample.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import com.ramcosta.composedestinations.spec.DestinationSpec
import ferm.jonny.feature_movies_presentation.movie_detail.MovieDetailsScreenNavigator

@ExperimentalComposeUiApi
class MovieDetailsScreenNavigatorImpl(
    private val currentDestination: DestinationSpec<*>,
    private val navController: NavController
) : MovieDetailsScreenNavigator {

    override fun navigateUp() {
        navController.navigateUp()
    }
}