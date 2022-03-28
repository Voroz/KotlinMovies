package ferm.jonny.architectureexample

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import ferm.jonny.architectureexample.navigation.Navigator
import ferm.jonny.architectureexample.navigation.RootNavGraph
import ferm.jonny.feature_start_presentation.StartNavGraph

@ExperimentalComposeUiApi
@Composable
fun MainScreen() {
    val appState: AppState = rememberAppState()
    val navController = appState.navController
    navController.enableOnBackPressed(false)

    Scaffold(
    scaffoldState = appState.scaffoldState,
    topBar = {
        TopAppBar(title = {
            Text(text = LocalContext.current.getString(R.string.app_name))
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