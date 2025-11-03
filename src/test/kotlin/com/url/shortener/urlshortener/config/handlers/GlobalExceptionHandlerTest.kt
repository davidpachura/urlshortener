package com.url.shortener.urlshortener.config.handlers

import org.junit.jupiter.api.Test

class GlobalExceptionHandlerTest {
    private val handler = GlobalExceptionHandler()

    @Test
    fun `should handle exception`() {
        val exception = Exception("test")
        val response = handler.handleException(exception)

        assert(response.statusCode.value() == 500)
        assert(response.body?.message == "Unexpected error: test")
    }
}