package ferm.jonny.feature_movies_presentation.movies_overview

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ferm.jonny.core.domain.model.DataResult
import ferm.jonny.feature_movies_domain.model.MovieOverview
import ferm.jonny.feature_movies_domain.use_case.GetMovies
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesOverviewViewModel @Inject constructor(
    private val getMovies: GetMovies
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData(listOf<MovieOverview>())
    val moviesLiveData: LiveData<List<MovieOverview>> = _moviesLiveData

    private val _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String> = _errorMessageLiveData

    init {
        viewModelScope.launch {
            when (val moviesResult = getMovies(10)) {
                is DataResult.Error -> {
                    _errorMessageLiveData.value = moviesResult.error.message!!
                }
                is DataResult.Success -> {
                    _moviesLiveData.value = moviesResult.data
                }
            }
        }
    }
}