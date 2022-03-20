package ferm.jonny.architectureexample.features.movies.presentation.movie_detail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ferm.jonny.architectureexample.core.domain.model.DataResult
import ferm.jonny.architectureexample.features.movies.domain.model.MovieDetails
import ferm.jonny.architectureexample.features.movies.domain.use_case.GetMovieDetails
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetails: GetMovieDetails,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailsLiveData = MutableLiveData<MovieDetails>()
    val detailsLiveData: LiveData<MovieDetails> = _detailsLiveData
    private val _movieId: Int = savedStateHandle.get("movieId")!!

    init {
        viewModelScope.launch {
            when (val detailsResult = getMovieDetails(_movieId)) {
                is DataResult.Error -> {
                    // TODO: notify error state and then open snackbar or dialog in composable screen
                }
                is DataResult.Success -> {
                    _detailsLiveData.value = detailsResult.data
                }
            }
        }
    }
}