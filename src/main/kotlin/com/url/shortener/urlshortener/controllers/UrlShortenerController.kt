package com.url.shortener.urlshortener.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UrlShortenerController {

    @GetMapping("/healthcheck")
    fun healthcheck(): String {
        return "OK"
    }
}