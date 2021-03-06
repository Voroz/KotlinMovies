package ferm.jonny.feature_movies_presentation.movies_overview

import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import ferm.jonny.core.domain.PresentationUtil
import ferm.jonny.feature_movies_domain.model.MovieOverview

@Destination(start = true)
@Composable
fun MovieOverviewScreen(
    viewModel: MoviesOverviewViewModel = hiltViewModel(),
    presentationUtil: PresentationUtil,
    navigator: MoviesOverviewScreenNavigator
) {
    val movieOverviews: List<MovieOverview> by viewModel.moviesLiveData.observeAsState(listOf())
    val errorMessage: String? by viewModel.errorMessageLiveData.observeAsState()

    errorMessage?.let {
        presentationUtil.showSnackbar(it, SnackbarDuration.Short)
    }

    MovieOverviewList(movieOverviews) {
            id: Int -> navigator.navigateToDetails(id)
    }
}