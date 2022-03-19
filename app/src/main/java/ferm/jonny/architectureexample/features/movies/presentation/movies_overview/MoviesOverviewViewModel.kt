package ferm.jonny.architectureexample.features.movies.presentation.movies_overview

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ferm.jonny.architectureexample.core.domain.model.DataResult
import ferm.jonny.architectureexample.features.movies.data.repository.MovieRepository
import ferm.jonny.architectureexample.features.movies.domain.model.FetchResourceError
import ferm.jonny.architectureexample.features.movies.domain.model.MovieOverview
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesOverviewViewModel @Inject constructor(
    private val _movieRepository: MovieRepository
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData(listOf<MovieOverview>())
    val moviesLiveData: LiveData<List<MovieOverview>> = _moviesLiveData

    init {
        viewModelScope.launch {
            when (val moviesResult = _movieRepository.getMovies(1)) {
                is DataResult.Error -> {
                    when (moviesResult.error.data) {
                        FetchResourceError.NoConnection -> TODO()
                        FetchResourceError.UnAuthorized -> TODO()
                        FetchResourceError.NotFound -> TODO()
                        FetchResourceError.Unknown -> moviesResult.error.message?.let { message -> Log.d("MainActivity", message) }
                    }
                }
                is DataResult.Success -> {
                    val data = moviesResult.data
                    _moviesLiveData.value = data
                }
            }
        }
    }
}