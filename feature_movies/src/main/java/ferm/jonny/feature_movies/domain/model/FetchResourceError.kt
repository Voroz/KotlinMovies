package ferm.jonny.feature_movies.domain.model

enum class FetchResourceError {
    NoConnection,
    UnAuthorized,
    NotFound,
    Unknown
}