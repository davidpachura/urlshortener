package com.url.shortener.urlshortener.models.creations

import com.url.shortener.urlshortener.models.entities.UrlEntity

data class UrlCreation (val url: String) {
    fun toUrlEntity(): UrlEntity {

        return UrlEntity(url = url, shortCode = "shortCode")
    }
}