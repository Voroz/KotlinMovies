package ferm.jonny.architectureexample.core.domain.model

data class ActionError<T>(
    val data: T,
    val message: String?
)