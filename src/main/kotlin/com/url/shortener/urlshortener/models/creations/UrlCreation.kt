package com.url.shortener.urlshortener.models.creations

import com.url.shortener.urlshortener.models.entities.UrlEntity
import java.util.*

data class UrlCreation (val url: String) {
    fun toUrlEntity(): UrlEntity {
        val code = UUID.randomUUID().toString().substring(0, 8)

        return UrlEntity(url = url, shortCode = code)
    }
}