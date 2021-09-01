package com.pinest94.retrofitdemo.model

import java.time.LocalDateTime

data class Todo(
    var id: Long? = null,
    var content: String? = null,
    var done: Boolean = false,
)