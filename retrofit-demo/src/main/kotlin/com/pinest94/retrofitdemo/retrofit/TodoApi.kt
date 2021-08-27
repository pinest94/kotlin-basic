package com.pinest94.retrofitdemo.retrofit

import com.pinest94.retrofitdemo.retrofit.dto.TodoDto
import com.pinest94.retrofitdemo.retrofit.network.NetworkResponse
import com.pinest94.retrofitdemo.utils.TodoUrl
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TodoApi {

    // TODO: Error 사용하지 말고 Customizing Error(enum class) 만들어서 사용할 것

    @GET(TodoUrl.RETRIEVE_TODO_LIST)
    suspend fun getTodoList(): NetworkResponse<List<TodoDto>, Error>

    @GET(TodoUrl.RETRIEVE_TODO)
    suspend fun getTodo(@Path("id") id: Long): NetworkResponse<TodoDto, Error>

    @POST(TodoUrl.CREATE_TODO)
    suspend fun createTodo(@Body todoDto: TodoDto): NetworkResponse<TodoDto, Error>
}
