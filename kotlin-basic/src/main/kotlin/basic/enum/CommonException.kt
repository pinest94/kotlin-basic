package basic.enum

import basic.enum.CommonException.*

enum class CommonException(val code: Int, val message: String) {
    OK(200, "OK"),
    CREATED(201, "CREATED"),
    NOT_FOUND(404, "NOT FOUND DATA"),
    INTERNAL_ERROR(500, "INTERNAL ERROR");

    fun getExceptionMessage() = "$code : $message"
}

fun getMessage(commonException: CommonException) = when(commonException) {
    OK, CREATED -> "SUCCESS"
    NOT_FOUND -> "INPUT ARGUMENT ERROR"
    INTERNAL_ERROR -> "SERVER ERROR"
}