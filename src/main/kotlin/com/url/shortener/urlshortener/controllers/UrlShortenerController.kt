package com.url.shortener.urlshortener.controllers

import com.url.shortener.urlshortener.models.creations.UrlCreation
import com.url.shortener.urlshortener.models.errors.UrlError
import com.url.shortener.urlshortener.models.errors.UrlError.*
import com.url.shortener.urlshortener.services.UrlShortenerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/urls")
class UrlShortenerController(
    private val urlShortenerService: UrlShortenerService
) {

    @GetMapping("/healthcheck")
    fun healthcheck(): String {
        return "OK"
    }

    @GetMapping("/{shortCode}")
    fun getUrl(@PathVariable shortCode: String): ResponseEntity<Any> =
        urlShortenerService.getOriginalUrl(shortCode).fold(
            ifLeft = { error -> mapError(error) },
            ifRight = { entity -> ResponseEntity.ok(entity) }
        )

    @PostMapping("/shorten")
    fun shortenUrl(@RequestParam url: String): ResponseEntity<Any> {
        val urlCreation = UrlCreation(url = url)
        return urlShortenerService.shortenUrl(urlCreation).fold(
            ifLeft = { error -> mapError(error) },
            ifRight = { shortCode -> ResponseEntity.ok(shortCode) }
        )
    }

    private fun mapError(error: UrlError): ResponseEntity<Any> =
        when (error) {
            is InvalidUrl -> ResponseEntity.badRequest().body(error)
            is UrlNotFound -> ResponseEntity.notFound().build()
            is Unexpected -> ResponseEntity.internalServerError().body(error)
            is BadRequest -> ResponseEntity.badRequest().body(error)
            is Conflict -> ResponseEntity.status(409).body(error)
        }
}