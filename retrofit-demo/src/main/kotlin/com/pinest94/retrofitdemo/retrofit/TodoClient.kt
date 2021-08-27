package com.pinest94.retrofitdemo.retrofit

import com.fasterxml.jackson.databind.ObjectMapper
import com.pinest94.retrofitdemo.retrofit.dto.TodoDto
import com.pinest94.retrofitdemo.retrofit.network.NetworkResponse
import com.pinest94.retrofitdemo.retrofit.network.NetworkResponseAdapterFactory
import com.pinest94.retrofitdemo.utils.TODO_API
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Component
class TodoClient(
    private val todoObjectMapper: ObjectMapper
) {

    private val client =
        Retrofit.Builder()
            .baseUrl(TODO_API.BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(JacksonConverterFactory.create(todoObjectMapper))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(TodoApi::class.java)

    suspend fun getTodo(id: Long): TodoDto {
        return when (val networkResponse = client.getTodo(id)) {
            is NetworkResponse.Success -> networkResponse.body
            is NetworkResponse.ApiError -> TodoDto(0L, "not content", false)
            else -> TodoDto(0L, "unknown error", false)
        }
    }

    suspend fun getTodoAll(): List<TodoDto> {
        return when (val networkResponse = client.getTodoList()) {
            is NetworkResponse.Success -> networkResponse.body
            else -> listOf()
        }
    }
}