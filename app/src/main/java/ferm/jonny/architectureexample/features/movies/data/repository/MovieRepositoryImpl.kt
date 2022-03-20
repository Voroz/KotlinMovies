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
import kotlin.math.min

// TODO: Handle more exceptions and convert to corresponding FetchResourceError
class MovieRepositoryImpl(
    private val api: MovieDbApi
) : MovieRepository {

    // This could be a flow since we do multiple requests if count is high, but since it isn't, I will skip that
    override suspend fun getMovies(count: Int): DataResult<List<MovieOverview>, FetchResourceError> {
        return try {
            val movieOverviews = mutableListOf<MovieOverview>()
            var currentPage = 1

            while (movieOverviews.size < count) {
                val movieOverviewsDto = api.getTopMovies(currentPage).await()
                movieOverviews.addAll(movieOverviewsDto.results.map { it.toMovieOverview() })
                currentPage++
            }

            DataResult.Success(movieOverviews.subList(0, min(movieOverviews.size, count)))
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