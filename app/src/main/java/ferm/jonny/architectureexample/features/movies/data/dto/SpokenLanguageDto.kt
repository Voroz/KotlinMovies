package ferm.jonny.architectureexample.features.movies.data.dto


import com.google.gson.annotations.SerializedName

data class SpokenLanguageDto(
    @SerializedName("iso_639_1")
    val iso6391: String,
    val name: String
)