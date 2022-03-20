package ferm.jonny.architectureexample.core.domain.model

sealed class ActionResult<out E: Any> {
    object Success: ActionResult<Nothing>()
    data class Error<E: Any>(val error: ActionError<E>): ActionResult<E>()
}