package com.pinest94.retrofitdemo.retrofit

import com.fasterxml.jackson.databind.ObjectMapper
import com.pinest94.retrofitdemo.retrofit.dto.TodoDto
import com.pinest94.retrofitdemo.retrofit.network.NetworkResponse
import com.pinest94.retrofitdemo.retrofit.network.NetworkResponseAdapterFactory
import com.pinest94.retrofitdemo.utils.TodoUrl
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Component
class TodoClient(
    private var todoObjectMapper: ObjectMapper
) {

    private val client =
        Retrofit.Builder()
            .baseUrl(TodoUrl.BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            // .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create(todoObjectMapper))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(TodoApi::class.java)

    suspend fun getTodo(id: Long?): TodoDto {
        // Error processing requirement except success case
        return when (val networkResponse = client.getTodo(id!!)) {
            is NetworkResponse.Success -> networkResponse.body
            is NetworkResponse.ApiError -> TodoDto(0L, "api error", false)
            is NetworkResponse.NetworkError, is NetworkResponse.UnknownError -> TodoDto(0L, "unknown error", false)
        }
    }

    suspend fun getTodoAll(): List<TodoDto> {
        // TODO: check error processing
        return when (val networkResponse = client.getTodoList()) {
            is NetworkResponse.Success -> networkResponse.body
            // TODO: empty list -> fix error code(requirement)
            is NetworkResponse.ApiError -> listOf(TodoDto(0L, "api error", false))
            is NetworkResponse.NetworkError -> listOf(TodoDto(0L, "network error", false))
            is NetworkResponse.UnknownError -> listOf(TodoDto(0L, "unknown error", false))
        }
    }

    suspend fun createTodo(todoDto: TodoDto): TodoDto {
        return when (val networkResponse = client.createTodo(todoDto)) {
            is NetworkResponse.Success -> networkResponse.body
            else -> TodoDto(0L, "not created", false)
        }
    }
}