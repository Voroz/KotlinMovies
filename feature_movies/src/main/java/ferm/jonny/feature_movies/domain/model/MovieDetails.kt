package ferm.jonny.feature_movies.domain.model

data class MovieDetails(
    val id: Int,
    val backdropPath: String?,
    val posterPath: String?,
    val budget: Int,
    val genres: List<String>,
    val homepage: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)