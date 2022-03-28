package ferm.jonny.core.domain

import androidx.compose.material.SnackbarDuration

interface PresentationUtil {
    fun showSnackbar(errorMessage: String, duration: SnackbarDuration)
}