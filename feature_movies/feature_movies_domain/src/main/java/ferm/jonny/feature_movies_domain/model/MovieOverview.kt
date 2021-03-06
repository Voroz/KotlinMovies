package ferm.jonny.feature_movies_domain.model

data class MovieOverview(
    val id: Int,
    val backdropPath: String?,
    val posterPath: String?,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)