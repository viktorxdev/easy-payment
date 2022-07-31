package ru.viktorxdev.easypayment.web

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.viktorxdev.easypayment.AppException

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    private val log = KotlinLogging.logger { }

    @ExceptionHandler(AppException::class)
    fun handleAppException(ex: AppException): ResponseEntity<BaseResponse<Any>> =
        ResponseEntity
            .status(ex.status)
            .body(ex.message.baseError)


    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<BaseResponse<Any>> {
        log.error(ex) { ex.message }
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ex.message.baseError)
    }
}