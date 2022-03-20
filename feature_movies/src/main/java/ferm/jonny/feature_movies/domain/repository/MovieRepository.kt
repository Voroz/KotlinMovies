package ferm.jonny.feature_movies.domain.repository

import ferm.jonny.core.domain.model.DataResult
import ferm.jonny.feature_movies.domain.model.FetchResourceError
import ferm.jonny.feature_movies.domain.model.MovieDetails
import ferm.jonny.feature_movies.domain.model.MovieOverview

interface MovieRepository {
    suspend fun getMovies(count: Int): DataResult<List<MovieOverview>, FetchResourceError>
    suspend fun getMovieDetails(id: Int): DataResult<MovieDetails, FetchResourceError>
}