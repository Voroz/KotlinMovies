package ferm.jonny.feature_movies_presentation.movies_overview

interface MoviesOverviewScreenNavigator {
    fun navigateToDetails(movieId: Int)
    fun navigateUp()
}