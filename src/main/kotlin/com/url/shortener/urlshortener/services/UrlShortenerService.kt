package com.url.shortener.urlshortener.services

import com.url.shortener.urlshortener.models.creations.UrlCreation
import com.url.shortener.urlshortener.repositories.UrlShortenerRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UrlShortenerService(
    private val urlShortenerRepository: UrlShortenerRepository
) {
    fun getOriginalUrl(shortCode: String): String? {
        return urlShortenerRepository.getByShortCode(shortCode).url
    }

    @Transactional
    fun shortenUrl(urlCreation: UrlCreation): String {
        val urlEntity = urlCreation.toUrlEntity()
        return urlShortenerRepository.save(urlEntity).shortCode
    }
}