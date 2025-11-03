package com.url.shortener.urlshortener.config.handlers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {


    @ExceptionHandler(Exception::class, Error::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse("Unexpected error: ${e.message}"),
            org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
        )

    data class ErrorResponse(val message: String)
}