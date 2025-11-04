package com.url.shortener.urlshortener.models.errors

sealed class UrlError {
    data class InvalidUrl(val message: String) : UrlError()
    data class UrlNotFound(val message: String) : UrlError()
    data class UrlExpired(val message: String) : UrlError()
    data class Conflict(val message: String) : UrlError()
    data class Unexpected(val message: String) : UrlError()
    data class BadRequest(val message: String) : UrlError()
}