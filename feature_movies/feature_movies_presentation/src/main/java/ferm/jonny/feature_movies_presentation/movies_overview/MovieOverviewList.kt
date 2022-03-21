package ferm.jonny.feature_movies_presentation.movies_overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ferm.jonny.feature_movies_domain.model.MovieOverview

@Composable
fun MovieOverviewList(data: List<MovieOverview>, movieTapped: (id: Int) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(data) { _, movieOverview ->
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .clickable {
                        movieTapped(movieOverview.id)
                    }
            ) {
                MovieOverviewCard(imageUrl = movieOverview.backdropPath)
            }
        }
    }
}