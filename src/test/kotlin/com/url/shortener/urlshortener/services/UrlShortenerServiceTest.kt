package com.url.shortener.urlshortener.services

import com.url.shortener.urlshortener.models.creations.UrlCreation
import com.url.shortener.urlshortener.models.entities.UrlEntity
import com.url.shortener.urlshortener.repositories.UrlShortenerRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class UrlShortenerServiceTest {

    @Mock
    private lateinit var urlShortenerRepository: UrlShortenerRepository

    @InjectMocks
    private lateinit var urlShortenerService: UrlShortenerService

    @Test
    fun `should shorten provided url`() {
        val urlCreation = UrlCreation(url = "url")

        whenever(urlShortenerRepository.save(any<UrlEntity>())).thenReturn(UrlEntity(url = urlCreation.url, shortCode = "shortCode"))

        val result = urlShortenerService.shortenUrl(urlCreation)

        assert(result == "shortCode")
    }

    @Test
    fun `should return original url when it exists`() {
        val shortCode = "shortCode"
        whenever(urlShortenerRepository.getByShortCode(shortCode)).thenReturn(UrlEntity(url = "url", shortCode = shortCode))
        val result = urlShortenerService.getOriginalUrl(shortCode)
        assert(result == "url")
    }
}