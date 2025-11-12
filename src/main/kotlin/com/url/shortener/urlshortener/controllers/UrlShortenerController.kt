package com.url.shortener.urlshortener.controllers

import com.url.shortener.urlshortener.models.creations.UrlCreation
import com.url.shortener.urlshortener.services.UrlShortenerService
import com.url.shortener.urlshortener.utils.respond
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
        urlShortenerService.getOriginalUrl(shortCode).respond()

    @PostMapping("/shorten")
    fun shortenUrl(@RequestParam url: String): ResponseEntity<Any> {
        val urlCreation = UrlCreation(url = url)
        return urlShortenerService.shortenUrl(urlCreation).respond()
    }

    @PostMapping("/shorten/batch")
    fun shortenUrls(@RequestParam urls: List<String>): ResponseEntity<Any> {
        val urlCreations = urls.map { url ->
            UrlCreation(url = url)
        }

        return urlShortenerService.shortenUrls(urlCreations).respond()
    }
}