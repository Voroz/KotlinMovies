package ferm.jonny.architectureexample.features.movies.presentation.movies_overview

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ferm.jonny.architectureexample.core.domain.model.DataResult
import ferm.jonny.architectureexample.features.movies.data.repository.MovieRepository
import ferm.jonny.architectureexample.features.movies.domain.model.FetchResourceError
import ferm.jonny.architectureexample.features.movies.domain.model.MovieOverview
import ferm.jonny.architectureexample.features.movies.domain.use_case.GetMovies
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesOverviewViewModel @Inject constructor(
    private val getMovies: GetMovies
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData(listOf<MovieOverview>())
    val moviesLiveData: LiveData<List<MovieOverview>> = _moviesLiveData

    init {
        viewModelScope.launch {
            when (val moviesResult = getMovies(10)) {
                is DataResult.Error -> {
                    // TODO: notify error state and then open snackbar or dialog in composable screen
                }
                is DataResult.Success -> {
                    _moviesLiveData.value = moviesResult.data
                }
            }
        }
    }
}