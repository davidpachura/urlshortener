package com.url.shortener.urlshortener.utils

import arrow.core.Either
import com.url.shortener.urlshortener.models.errors.UrlError
import org.springframework.http.ResponseEntity

fun Either<UrlError, Any>.respond(): ResponseEntity<Any> =
    fold({ error -> mapError(error) }, { value -> ResponseEntity.ok(value) })

private fun mapError(error: UrlError): ResponseEntity<Any> =
    when (error) {
        is UrlError.InvalidUrl -> ResponseEntity.badRequest().body(error)
        is UrlError.UrlNotFound -> ResponseEntity.notFound().build()
        is UrlError.Unexpected -> ResponseEntity.internalServerError().body(error)
        is UrlError.BadRequest -> ResponseEntity.badRequest().body(error)
        is UrlError.Conflict -> ResponseEntity.status(409).body(error)
    }