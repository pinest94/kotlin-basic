package com.pinest94.retrofitdemo.utils

object Constants {

}

enum class ResponseState {
    OKAY,
    FAIL
}

object TodoUrl {
    const val BASE_URL = "http://localhost:8080/"
    const val RETRIEVE_TODO = "todos/{id}"
    const val RETRIEVE_TODO_LIST = "todos"
    const val CREATE_TODO = "todos"
}