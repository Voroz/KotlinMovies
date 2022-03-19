package ferm.jonny.architectureexample.features.movies.data.repository

import ferm.jonny.architectureexample.core.domain.model.DataResult
import ferm.jonny.architectureexample.core.domain.model.ActionError
import ferm.jonny.architectureexample.features.movies.data.data_source.MovieDbApi
import ferm.jonny.architectureexample.features.movies.data.dto.toMovieDetails
import ferm.jonny.architectureexample.features.movies.data.dto.toMovieOverview
import ferm.jonny.architectureexample.features.movies.domain.model.FetchResourceError
import ferm.jonny.architectureexample.features.movies.domain.model.MovieDetails
import ferm.jonny.architectureexample.features.movies.domain.model.MovieOverview
import retrofit2.await
import javax.inject.Inject

// TODO: Handle more exceptions and convert to corresponding FetchResourceError
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieDbApi
) : MovieRepository {

    override suspend fun getMovies(page: Int): DataResult<List<MovieOverview>, FetchResourceError> {
        return try {
            val movieOverviewsDto = api.getTopMovies(page).await()
            val movieOverviews = movieOverviewsDto.results.map {
                it.toMovieOverview()
            }

            DataResult.Success(movieOverviews)
        } catch (e: Exception) {
            DataResult.Error(ActionError(FetchResourceError.Unknown, e.message))
        }
    }

    override suspend fun getMovieDetails(id: Int): DataResult<MovieDetails, FetchResourceError> {
        return try {
            val movieDetailsDto = api.getMovieDetails(id).await()
            val movieDetails = movieDetailsDto.toMovieDetails()

            DataResult.Success(movieDetails)
        } catch (e: Exception) {
            DataResult.Error(ActionError(FetchResourceError.Unknown, e.message))
        }
    }
}