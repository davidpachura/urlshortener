package com.url.shortener.urlshortener.services

import com.url.shortener.urlshortener.models.creations.UrlCreation
import com.url.shortener.urlshortener.models.entities.UrlEntity
import com.url.shortener.urlshortener.repositories.UrlShortenerRepository
import io.kotest.assertions.arrow.core.shouldBeRight
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.util.regex.Pattern

@ExtendWith(MockitoExtension::class)
class UrlShortenerServiceTest {

    @Mock
    private lateinit var urlShortenerRepository: UrlShortenerRepository

    @InjectMocks
    private lateinit var urlShortenerService: UrlShortenerService

    @Test
    fun `should shorten provided url`() {
        val urlCreation = UrlCreation(url = "url")

        whenever(urlShortenerRepository.save(any<UrlEntity>())).thenReturn(urlCreation.toUrlEntity())

        val result = urlShortenerService.shortenUrl(urlCreation).shouldBeRight()

        val pattern = Pattern.compile("^[0-9a-fA-F]{8}$")

        assert(pattern.matcher(result).matches())
    }

    @Test
    fun `should return original url when it exists`() {
        val shortCode = "shortCode"
        whenever(urlShortenerRepository.findByShortCode(shortCode)).thenReturn(UrlEntity(url = "url", shortCode = shortCode))
        val result = urlShortenerService.getOriginalUrl(shortCode).shouldBeRight()
        assert(result == "url")
    }
}