package ferm.jonny.domain.repository

import ferm.jonny.core.domain.model.DataResult
import ferm.jonny.domain.model.FetchResourceError
import ferm.jonny.domain.model.MovieDetails
import ferm.jonny.domain.model.MovieOverview

interface MovieRepository {
    suspend fun getMovies(count: Int): DataResult<List<MovieOverview>, FetchResourceError>
    suspend fun getMovieDetails(id: Int): DataResult<MovieDetails, FetchResourceError>
}