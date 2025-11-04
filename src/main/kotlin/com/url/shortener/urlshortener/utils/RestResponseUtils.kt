package com.url.shortener.urlshortener.utils

import arrow.core.Either
import com.url.shortener.urlshortener.models.errors.UrlError
import com.url.shortener.urlshortener.models.errors.UrlError.*
import org.springframework.http.ResponseEntity

fun Either<UrlError, Any>.respond(): ResponseEntity<Any> =
    fold({ error -> mapError(error) }, { value -> ResponseEntity.ok(value) })

private fun mapError(error: UrlError): ResponseEntity<Any> =
    when (error) {
        is InvalidUrl -> ResponseEntity.badRequest().body(error)
        is UrlNotFound -> ResponseEntity.notFound().build()
        is UrlExpired -> ResponseEntity.notFound().build()
        is Unexpected -> ResponseEntity.internalServerError().body(error)
        is BadRequest -> ResponseEntity.badRequest().body(error)
        is Conflict -> ResponseEntity.status(409).body(error)
    }