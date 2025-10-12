package com.url.shortener.urlshortener.controllers

import com.url.shortener.urlshortener.models.creations.UrlCreation
import com.url.shortener.urlshortener.services.UrlShortenerService
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
    fun getUrl(@PathVariable shortCode: String): String? {
        return urlShortenerService.getOriginalUrl(shortCode)
    }

    @PostMapping("/shorten")
    fun shortenUrl(@RequestParam url: String): String {
        val urlCreation = UrlCreation(url = url)
        return urlShortenerService.shortenUrl(urlCreation)
    }
}