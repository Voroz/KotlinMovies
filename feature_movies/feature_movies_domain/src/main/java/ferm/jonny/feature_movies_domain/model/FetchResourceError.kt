package ferm.jonny.feature_movies_domain.model

enum class FetchResourceError {
    NoConnection,
    UnAuthorized,
    NotFound,
    Unknown
}