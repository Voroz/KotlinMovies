package ferm.jonny.architectureexample.features.movies.data.data_source

import ferm.jonny.architectureexample.features.movies.data.dto.MovieDetailsDto
import ferm.jonny.architectureexample.features.movies.data.dto.MovieOverviewsDto
import retrofit2.Call
import retrofit2.http.*


interface MovieDbApi {
    @GET("/3/movie/top_rated")
    fun getTopMovies(@Query("page") page: Int = 1): Call<MovieOverviewsDto>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Call<MovieDetailsDto>
}