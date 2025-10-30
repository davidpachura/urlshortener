package com.url.shortener.urlshortener.services

import com.url.shortener.urlshortener.models.creations.UrlCreation
import com.url.shortener.urlshortener.models.errors.UrlError.*
import com.url.shortener.urlshortener.repositories.UrlShortenerRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import arrow.core.Either
import arrow.core.left
import arrow.core.raise.either
import com.url.shortener.urlshortener.models.errors.UrlError

@Service
class UrlShortenerService(
    private val urlShortenerRepository: UrlShortenerRepository
) {
    fun getOriginalUrl(shortCode: String): Either<UrlError, String> = either {
        val urlEntity = urlShortenerRepository.findByShortCode(shortCode)?.url
            ?: return UrlNotFound("Url not found").left()
        urlEntity
    }

    @Transactional
    fun shortenUrl(urlCreation: UrlCreation): Either<UrlError, String> = Either.catch {
        val urlEntity = urlCreation.toUrlEntity()
        urlShortenerRepository.save(urlEntity).shortCode
    }.mapLeft {
        when (it) {
            is IllegalArgumentException -> InvalidUrl("Invalid url")
            else -> Unexpected("Unexpected error")
        }
    }
}