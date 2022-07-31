package ru.viktorxdev.easypayment.web

data class BaseResponse<T>(
    val data: T? = null,
    val error: String? = null
) {
    val success: Boolean = error == null
}

val <T> T.baseResponse get() = BaseResponse(data = this)
val String?.baseError get() = BaseResponse<Any>(error = this)
val <T> T?.voidResult get() = BaseResponse<Any>()
