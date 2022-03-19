package ferm.jonny.architectureexample.features.movies.domain.model

enum class FetchResourceError {
    NoConnection,
    UnAuthorized,
    NotFound,
    Unknown
}