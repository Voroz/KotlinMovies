package ferm.jonny.architectureexample.features.movies.data.repository

import ferm.jonny.architectureexample.core.domain.model.DataResult
import ferm.jonny.architectureexample.features.movies.domain.model.FetchResourceError
import ferm.jonny.architectureexample.features.movies.domain.model.MovieDetails
import ferm.jonny.architectureexample.features.movies.domain.model.MovieOverview

interface MovieRepository {
    suspend fun getMovies(count: Int): DataResult<List<MovieOverview>, FetchResourceError>
    suspend fun getMovieDetails(id: Int): DataResult<MovieDetails, FetchResourceError>
}