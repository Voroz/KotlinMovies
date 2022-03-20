package ferm.jonny.feature_movies.data.dto


import com.google.gson.annotations.SerializedName
import ferm.jonny.core.Constants
import ferm.jonny.feature_movies.domain.model.MovieOverview

data class MovieOverviewDto(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

fun MovieOverviewDto.toMovieOverview() : MovieOverview {
    return MovieOverview(
        id = id,
        backdropPath = "${Constants.movieDbImagesUrl}${backdropPath}",
        posterPath = "${Constants.movieDbImagesUrl}${posterPath}",
        originalLanguage = originalLanguage,
        overview= overview,
        popularity = popularity,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}