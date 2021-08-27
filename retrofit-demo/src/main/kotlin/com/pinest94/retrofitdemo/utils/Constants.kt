package com.pinest94.retrofitdemo.utils

object Constants {

}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}

object TODO_API {
    const val BASE_URL = "http://localhost:8080/"
    const val RETRIEVE_TODO = "todos/{id}"
    const val RETRIEVE_TODO_LIST = "todos"
    const val CREATE_TODO = "todos"
}