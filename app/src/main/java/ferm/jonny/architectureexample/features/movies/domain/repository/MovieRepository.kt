package ferm.jonny.architectureexample.features.movies.data.repository

import ferm.jonny.architectureexample.core.domain.model.ActionResult
import ferm.jonny.architectureexample.features.movies.domain.model.FetchResourceError
import ferm.jonny.architectureexample.features.movies.domain.model.MovieDetails
import ferm.jonny.architectureexample.features.movies.domain.model.MovieOverview

interface MovieRepository {
    suspend fun getMovies(page: Int): ActionResult<List<MovieOverview>, FetchResourceError>
    suspend fun getMovieDetails(id: Int): ActionResult<MovieDetails, FetchResourceError>
}