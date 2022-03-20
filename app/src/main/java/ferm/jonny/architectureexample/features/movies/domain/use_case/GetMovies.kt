package ferm.jonny.architectureexample.features.movies.domain.use_case

import android.util.Log
import ferm.jonny.architectureexample.core.domain.model.ActionError
import ferm.jonny.architectureexample.core.domain.model.DataResult
import ferm.jonny.architectureexample.features.movies.data.repository.MovieRepository
import ferm.jonny.architectureexample.features.movies.domain.model.FetchResourceError
import ferm.jonny.architectureexample.features.movies.domain.model.MovieOverview

class GetMovies(
    private val _movieRepository: MovieRepository
) {
    suspend operator fun invoke(count: Int): DataResult<List<MovieOverview>, FetchResourceError> {
        return when (val moviesResult = _movieRepository.getMovies(count)) {
            is DataResult.Error -> {

                moviesResult.error.message?.let { exceptionMessage -> Log.d("GetMovies", exceptionMessage) }

                val userMessage = when (moviesResult.error.data) {
                    FetchResourceError.NoConnection -> "Sorry, looks like something's wrong with the connection."
                    FetchResourceError.UnAuthorized -> "Your application is not authorized to view the list of movies"
                    FetchResourceError.NotFound -> "Ops, no movies were found."
                    FetchResourceError.Unknown -> "Sorry, an unknown error occurred when retrieving the movie list"
                }

                DataResult.Error(ActionError(moviesResult.error.data, userMessage))
            }
            is DataResult.Success -> {
                DataResult.Success(moviesResult.data)
            }
        }
    }
}