package ferm.jonny.domain.use_case

import android.util.Log
import ferm.jonny.core.domain.model.ActionError
import ferm.jonny.core.domain.model.DataResult
import ferm.jonny.domain.model.FetchResourceError
import ferm.jonny.domain.model.MovieDetails
import ferm.jonny.domain.repository.MovieRepository

class GetMovieDetails(
    private val _movieRepository: MovieRepository
) {
    suspend operator fun invoke(id: Int): DataResult<MovieDetails, FetchResourceError> {
        return when (val detailsResult = _movieRepository.getMovieDetails(id)) {
            is DataResult.Error -> {

                detailsResult.error.message?.let { exceptionMessage -> Log.d("GetMovieDetails", exceptionMessage) }

                val userMessage = when (detailsResult.error.data) {
                    FetchResourceError.NoConnection -> "Sorry, looks like something's wrong with the connection."
                    FetchResourceError.UnAuthorized -> "Your application is not authorized to view the details of that movie."
                    FetchResourceError.NotFound -> "Ops, no details were found for that movie."
                    FetchResourceError.Unknown -> "Sorry, an unknown error occurred when retrieving the details for that movie."
                }

                DataResult.Error(ActionError(detailsResult.error.data, userMessage))
            }
            is DataResult.Success -> {
                DataResult.Success(detailsResult.data)
            }
        }
    }
}