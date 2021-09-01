package com.pinest94.retrofitdemo.retrofit.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.pinest94.retrofitdemo.utils.ResponseState

data class TodoDto (
    @JsonProperty("id") var id: Long?,
    @JsonProperty("content") var content: String,
    @JsonProperty("done") var done: Boolean,
    // TODO: status requirement
)

data class TodoErrorResponse(
    val responseCode: ResponseState,
    // TODO: check that string is sufficient(verify enum is required..)
    val errorMessages: String
)
