package ferm.jonny.feature_movies_data.dto


import com.google.gson.annotations.SerializedName

data class MovieOverviewsDto(
    val page: Int,
    val results: List<MovieOverviewDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)