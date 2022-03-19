package ferm.jonny.architectureexample.core.domain.model

sealed class DataResult<out T, out E> {
    data class Success<out T>(val data: T): DataResult<T, Nothing>()
    data class Error<E>(val error: ActionError<E>): DataResult<Nothing, E>()
}