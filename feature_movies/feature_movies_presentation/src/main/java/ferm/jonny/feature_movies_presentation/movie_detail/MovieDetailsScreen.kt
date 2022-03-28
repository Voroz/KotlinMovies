package ferm.jonny.feature_movies_presentation.movie_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import ferm.jonny.core.domain.PresentationUtil
import ferm.jonny.feature_movies_domain.model.MovieDetails


@Destination
@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    presentationUtil: PresentationUtil,
    movieId: Int
) {
    val movieDetails: MovieDetails? by viewModel.detailsLiveData.observeAsState()
    val errorMessage: String? by viewModel.errorMessageLiveData.observeAsState()

    errorMessage?.let {
        presentationUtil.showSnackbar(it, SnackbarDuration.Short)
    }

    movieDetails?.let {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            modifier = Modifier.fillMaxSize()
        ) {
            MovieDetailsCard(
                it.backdropPath, it.title, it.budget, it.genres,
                it.originalLanguage, it.overview, it.popularity, it.releaseDate,
                it.revenue, it.runtime, it.voteAverage, it.voteCount
            )
        }
    }
}