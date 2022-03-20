package ferm.jonny.feature_movies.presentation.movies_overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ferm.jonny.feature_movies.domain.model.MovieOverview
import ferm.jonny.feature_movies.presentation.destinations.MovieDetailsScreenDestination

@Destination(start = true)
@Composable
fun MovieOverviewScreen(
    viewModel: MoviesOverviewViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val movieOverviews: List<MovieOverview> by viewModel.moviesLiveData.observeAsState(listOf())

    MovieOverviewList(movieOverviews) {
            id: Int -> navigator.navigate(MovieDetailsScreenDestination(id), onlyIfResumed = true)
    }
}