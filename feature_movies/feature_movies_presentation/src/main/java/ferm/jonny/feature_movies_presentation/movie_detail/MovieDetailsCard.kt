package ferm.jonny.feature_movies_presentation.movie_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetailsCard(
    imageUrl: String?, title: String, budget: Int, genres: List<String>, originalLanguage: String,
    overview: String, popularity: Double, releaseDate: String, revenue: Int, runtime: Int,
    voteAverage: Double, voteCount: Int) {
    Card(
        modifier = Modifier.fillMaxSize(),
        elevation = 5.dp
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())) {
            GlideImage(
                imageModel = imageUrl,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(50.dp)
                        )
                    }
                },
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                val hours = runtime / 60
                val minutes = runtime % 60

                val texts = listOf(
                    Pair("Title: ", title),
                    Pair("Genres: ", genres.joinToString(", ")),
                    Pair("Language: ", originalLanguage),
                    Pair("Rating: ", "$voteAverage ($voteCount votes)"),
                    Pair("Runtime: ", "$hours:$minutes"),
                    Pair("Release date: ", releaseDate),
                    Pair("Description: ", overview),
                )

                texts.forEach {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(it.first)
                            }
                            append(it.second)
                        },
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}