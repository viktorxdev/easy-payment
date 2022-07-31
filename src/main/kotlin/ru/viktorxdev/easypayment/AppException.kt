package ru.viktorxdev.easypayment

import org.springframework.http.HttpStatus

sealed class AppException(message: String, val status: HttpStatus) : RuntimeException(message) {
    class KafkaException(message: String) : AppException(message, HttpStatus.INTERNAL_SERVER_ERROR)
}
