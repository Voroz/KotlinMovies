package ferm.jonny.presentation.movies_overview

interface MoviesOverviewScreenNavigator {
    fun navigateToDetails(movieId: Int)
    fun navigateUp()
}