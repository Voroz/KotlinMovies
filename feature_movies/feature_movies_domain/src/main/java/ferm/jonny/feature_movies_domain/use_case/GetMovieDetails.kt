package ferm.jonny.feature_movies_domain.use_case

import android.content.Context
import android.util.Log
import ferm.jonny.core.domain.model.ActionError
import ferm.jonny.core.domain.model.DataResult
import ferm.jonny.feature_movies_domain.R
import ferm.jonny.feature_movies_domain.model.FetchResourceError
import ferm.jonny.feature_movies_domain.model.MovieDetails
import ferm.jonny.feature_movies_domain.repository.MovieRepository

class GetMovieDetails(
    private val _context: Context,
    private val _movieRepository: MovieRepository
) {
    suspend operator fun invoke(id: Int): DataResult<MovieDetails, FetchResourceError> {
        return when (val detailsResult = _movieRepository.getMovieDetails(id)) {
            is DataResult.Error -> {

                detailsResult.error.message?.let { exceptionMessage -> Log.d("GetMovieDetails", exceptionMessage) }

                val userMessage = when (detailsResult.error.data) {
                    FetchResourceError.NoConnection -> _context.getString(ferm.jonny.core.R.string.no_connection)
                    FetchResourceError.UnAuthorized -> _context.getString(R.string.movie_details_unauthorized_access)
                    FetchResourceError.NotFound -> _context.getString(R.string.movie_details_resource_not_found)
                    FetchResourceError.Unknown -> _context.getString(R.string.movie_details_unknown_error)
                }

                DataResult.Error(ActionError(detailsResult.error.data, userMessage))
            }
            is DataResult.Success -> {
                DataResult.Success(detailsResult.data)
            }
        }
    }
}