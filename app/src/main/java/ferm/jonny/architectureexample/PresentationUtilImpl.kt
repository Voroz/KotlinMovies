package ferm.jonny.architectureexample

import androidx.compose.material.SnackbarDuration
import ferm.jonny.core.domain.PresentationUtil

class PresentationUtilImpl(private val onShowSnackbar: (String, SnackbarDuration) -> Unit) : PresentationUtil {

    override fun showSnackbar(errorMessage: String, duration: SnackbarDuration) {
        onShowSnackbar(errorMessage, duration)
    }
}