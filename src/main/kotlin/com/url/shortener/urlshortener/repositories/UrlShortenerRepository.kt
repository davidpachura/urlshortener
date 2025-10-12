package com.url.shortener.urlshortener.repositories

import com.url.shortener.urlshortener.models.entities.UrlEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID


interface UrlShortenerRepository : JpaRepository<UrlEntity, UUID> {
    fun getByShortCode(shortCode: String): UrlEntity
}
