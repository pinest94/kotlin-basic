package com.pinest94.retrofitdemo.retrofit.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.pinest94.retrofitdemo.utils.RESPONSE_STATE

data class TodoDto (
    @JsonProperty("id") var id: Long,
    @JsonProperty("content") var content: String,
    @JsonProperty("done") var done: Boolean,
)

data class TodoErrorResponse(
    val responseCode: RESPONSE_STATE,
    val errorMessages: String
)
