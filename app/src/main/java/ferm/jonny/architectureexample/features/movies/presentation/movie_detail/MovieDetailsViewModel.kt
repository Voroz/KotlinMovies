package ferm.jonny.architectureexample.features.movies.presentation.movie_detail

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ferm.jonny.architectureexample.core.domain.model.DataResult
import ferm.jonny.architectureexample.features.movies.data.repository.MovieRepository
import ferm.jonny.architectureexample.features.movies.domain.model.FetchResourceError
import ferm.jonny.architectureexample.features.movies.domain.model.MovieDetails
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val _movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailsLiveData = MutableLiveData<MovieDetails>()
    val detailsLiveData: LiveData<MovieDetails> = _detailsLiveData
    private var _movieId: Int = savedStateHandle.get("movieId")!!

    init {
        viewModelScope.launch {
            when (val detailsResult = _movieRepository.getMovieDetails(_movieId)) {
                is DataResult.Error -> {
                    when (detailsResult.error.data) {
                        FetchResourceError.NoConnection -> TODO()
                        FetchResourceError.UnAuthorized -> TODO()
                        FetchResourceError.NotFound -> TODO()
                        FetchResourceError.Unknown -> detailsResult.error.message?.let { message -> Log.d("MainActivity", message) }
                    }
                }
                is DataResult.Success -> {
                    _detailsLiveData.value = detailsResult.data
                }
            }
        }
    }
}