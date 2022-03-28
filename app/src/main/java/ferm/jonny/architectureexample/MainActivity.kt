package ferm.jonny.architectureexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint
import ferm.jonny.architectureexample.navigation.*
import ferm.jonny.core.domain.PresentationUtil
import ferm.jonny.feature_start_presentation.StartNavGraph

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val appState: AppState = rememberAppState()
            val navController = appState.navController
            navController.enableOnBackPressed(false)

            Scaffold(
                scaffoldState = appState.scaffoldState,
                topBar = {
                TopAppBar(title = {
                    Text(text = applicationContext.getString(R.string.app_name))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                }
                )
            }) {
                DestinationsNavHost(
                    navController = navController,
                    navGraph = RootNavGraph,
                    startRoute = StartNavGraph,
                    dependenciesContainerBuilder = {
                        dependency(
                            Navigator(
                                currentDestination = destination,
                                navController = navController
                            )
                        )
                        dependency(
                            PresentationUtilImpl { message, duration ->
                                appState.showSnackbar(message, duration)
                            })
                    }
                )
            }
        }
    }
}