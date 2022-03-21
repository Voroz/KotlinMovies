package ferm.jonny.presentation.movie_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import ferm.jonny.domain.model.MovieDetails


@Destination
@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    movieId: Int // This needs to be here for the automatic nav code generation so that it can be retrieved from savedStateHandle in the viewModel.
) {
    val movieDetails: MovieDetails? by viewModel.detailsLiveData.observeAsState()

    movieDetails?.let {
        MovieDetailsCard(
            it.backdropPath, it.title, it.budget, it.genres,
            it.originalLanguage, it.overview, it.popularity, it.releaseDate,
            it.revenue, it.runtime, it.voteAverage, it.voteCount
        )
    }
}