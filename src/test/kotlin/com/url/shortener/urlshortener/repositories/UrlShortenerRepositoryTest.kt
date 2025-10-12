package com.url.shortener.urlshortener.repositories

import com.url.shortener.urlshortener.models.entities.UrlEntity
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class UrlShortenerRepositoryTest: RepositoryTestBase() {
    @Autowired
    private lateinit var urlShortenerRepository: UrlShortenerRepository

    @Test
    fun `should save entity and find by short code`() {
        val urlEntity = UrlEntity(url = "url", shortCode = "shortCode")
        urlShortenerRepository.save(urlEntity)
        val savedEntity = urlShortenerRepository.getByShortCode("shortCode")

        assert(savedEntity.url == urlEntity.url)
        assert(savedEntity.shortCode == urlEntity.shortCode)
        assert(savedEntity.id != null)
    }
}