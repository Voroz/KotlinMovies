package ferm.jonny.architectureexample.core.domain.model

sealed class ActionResult<out T, out E> {
    data class Success<out T>(val data: T?): ActionResult<T, Nothing>()
    data class Error<E>(val error: ActionError<E>): ActionResult<Nothing, E>()
}