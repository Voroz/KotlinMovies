package ferm.jonny.architectureexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import ferm.jonny.architectureexample.core.Constants
import ferm.jonny.architectureexample.features.movies.data.repository.MovieRepository
import ferm.jonny.architectureexample.features.movies.domain.model.MovieOverview
import ferm.jonny.architectureexample.features.movies.presentation.movies_overview.MoviesOverviewViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
    }
}

@Destination(start = true)
@Composable
fun MovieOverviewScreen(viewModel: MoviesOverviewViewModel = hiltViewModel()) {
    val movieOverviews: List<MovieOverview> by viewModel.moviesLiveData.observeAsState(listOf())

    MovieOverviewList(movieOverviews)
}

@Composable
fun MovieOverviewList(data: List<MovieOverview>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(data) { _, movieOverview ->
            Box(
                modifier = Modifier.height(200.dp)) {
                val url = "${Constants.movieDbImagesUrl}${movieOverview.backdropPath}"
                MovieOverviewCard(imageUrl = url)
            }
        }
    }
}

@Composable
fun MovieOverviewCard(imageUrl: String, contentScale: ContentScale = ContentScale.Crop) {
    Card(
        modifier = Modifier.fillMaxSize(),
        elevation = 5.dp,
        shape = RoundedCornerShape(3.dp)
    ) {

        GlideImage(
            imageModel = imageUrl,
            contentDescription = "A description",
            contentScale = contentScale,
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        )
    }
}