package com.url.shortener.urlshortener.models.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "urls")
data class UrlEntity (
    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(nullable = false)
    val url: String,

    @Column(unique = true, nullable = false)
    val shortCode: String,

    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    fun isExpired(): Boolean = createdAt.isBefore(LocalDateTime.now().minusHours(24))
}