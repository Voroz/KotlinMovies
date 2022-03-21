package ferm.jonny.feature_movies_data.dto


import com.google.gson.annotations.SerializedName
import ferm.jonny.core.Constants
import ferm.jonny.feature_movies_domain.model.MovieDetails

data class MovieDetailsDto(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any,
    val budget: Int,
    val genres: List<GenreDto>,
    val homepage: String,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDto>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDto>,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)

fun MovieDetailsDto.toMovieDetails() : MovieDetails {
    return MovieDetails(
        id = id,
        backdropPath = "${Constants.movieDbImagesUrl}${backdropPath}",
        posterPath = "${Constants.movieDbImagesUrl}${posterPath}",
        budget = budget,
        genres = genres.map { it.name },
        homepage = homepage,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}