package com.pinest94.retrofitdemo.retrofit

import com.pinest94.retrofitdemo.model.Todo
import com.pinest94.retrofitdemo.retrofit.dto.TodoDto
import com.pinest94.retrofitdemo.retrofit.network.NetworkResponse
import com.pinest94.retrofitdemo.utils.TODO_API
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TodoApi {

    @GET(TODO_API.RETRIEVE_TODO_LIST)
    suspend fun getTodoList(): NetworkResponse<List<TodoDto>, Error>

    @GET(TODO_API.RETRIEVE_TODO)
    suspend fun getTodo(@Path("id") id: Long): NetworkResponse<TodoDto, Error>

    @POST(TODO_API.CREATE_TODO)
    suspend fun createTodo(@Body todo: Todo): NetworkResponse<TodoDto, Error>
}
