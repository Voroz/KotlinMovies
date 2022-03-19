package ferm.jonny.architectureexample.core.domain.model

sealed class ActionResult<out E> {
    object Success: ActionResult<Nothing>()
    data class Error<E>(val error: ActionError<E>): ActionResult<E>()
}