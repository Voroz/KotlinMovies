package ferm.jonny.feature_movies_domain.repository

import ferm.jonny.core.domain.model.DataResult
import ferm.jonny.feature_movies_domain.model.FetchResourceError
import ferm.jonny.feature_movies_domain.model.MovieDetails
import ferm.jonny.feature_movies_domain.model.MovieOverview

interface MovieRepository {
    suspend fun getMovies(count: Int): DataResult<List<MovieOverview>, FetchResourceError>
    suspend fun getMovieDetails(id: Int): DataResult<MovieDetails, FetchResourceError>
}