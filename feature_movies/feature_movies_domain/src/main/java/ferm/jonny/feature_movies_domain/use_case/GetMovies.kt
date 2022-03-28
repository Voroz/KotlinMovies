package ferm.jonny.feature_movies_domain.use_case

import android.content.Context
import android.util.Log
import ferm.jonny.core.domain.model.ActionError
import ferm.jonny.core.domain.model.DataResult
import ferm.jonny.feature_movies_domain.R
import ferm.jonny.feature_movies_domain.model.FetchResourceError
import ferm.jonny.feature_movies_domain.model.MovieOverview
import ferm.jonny.feature_movies_domain.repository.MovieRepository

class GetMovies(
    private val _context: Context,
    private val _movieRepository: MovieRepository
) {
    suspend operator fun invoke(count: Int): DataResult<List<MovieOverview>, FetchResourceError> {
        return when (val moviesResult = _movieRepository.getMovies(count)) {
            is DataResult.Error -> {

                moviesResult.error.message?.let { exceptionMessage -> Log.d("GetMovies", exceptionMessage) }

                val userMessage = when (moviesResult.error.data) {
                    FetchResourceError.NoConnection -> _context.getString(ferm.jonny.core.R.string.no_connection)
                    FetchResourceError.UnAuthorized -> _context.getString(R.string.movie_list_unauthorized_access)
                    FetchResourceError.NotFound -> _context.getString(R.string.movie_list_resource_not_found)
                    FetchResourceError.Unknown -> _context.getString(R.string.movie_list_unknown_error)
                }

                DataResult.Error(ActionError(moviesResult.error.data, userMessage))
            }
            is DataResult.Success -> {
                DataResult.Success(moviesResult.data)
            }
        }
    }
}