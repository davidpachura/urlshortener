package com.url.shortener.urlshortener.controllers

import arrow.core.Either
import com.url.shortener.urlshortener.models.creations.UrlCreation
import com.url.shortener.urlshortener.services.UrlShortenerService
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
@AutoConfigureMockMvc
class UrlShortenerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var urlShortenerService: UrlShortenerService

    @Test
    fun `should shorten provided url`() {
        whenever(urlShortenerService.shortenUrl(any<UrlCreation>())).thenReturn(Either.Right("shortCode"))

        mockMvc.perform(post("/api/v1/urls/shorten").param("url", "url"))
            .andExpect(status().isOk)
            .andExpect(content().string("shortCode"))
    }

    @Test
    fun `should return original url when it exists`() {
        whenever(urlShortenerService.getOriginalUrl(any<String>())).thenReturn(Either.Right("url"))

        mockMvc.perform(get("/api/v1/urls/{shortCode}", "shortCode"))
            .andExpect(status().isOk)
            .andExpect(content().string("url"))
    }
}