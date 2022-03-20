package ferm.jonny.architectureexample.core.domain.model

sealed class DataResult<out T: Any, out E: Any> {
    data class Success<out T: Any>(val data: T): DataResult<T, Nothing>()
    data class Error<E: Any>(val error: ActionError<E>): DataResult<Nothing, E>()
}