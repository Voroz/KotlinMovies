package ferm.jonny.feature_movies.presentation.movies_overview

interface MoviesOverviewScreenNavigator {
    fun navigateToDetails(movieId: Int)
    fun navigateUp()
}