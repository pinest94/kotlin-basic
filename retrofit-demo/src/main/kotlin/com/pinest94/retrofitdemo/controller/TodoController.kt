package com.pinest94.retrofitdemo.controller

import com.pinest94.retrofitdemo.retrofit.TodoClient
import com.pinest94.retrofitdemo.retrofit.dto.TodoDto
import org.springframework.web.bind.annotation.*
import retrofit2.http.Body

@RestController
@RequestMapping("todos")
class TodoController(
    private val todoClient: TodoClient
) {

    // TODO: TodoDto를 그냥 리턴하는 것보다 ResponseDto에 제네릭을 사용해서 response 해야할 것

    @GetMapping("/{id}")
    // TODO: id argument null check requirement
    suspend fun getTodo(@PathVariable("id") id: Long): TodoDto = todoClient.getTodo(id)

    @GetMapping("")
    suspend fun getTodoAll(): List<TodoDto> = todoClient.getTodoAll()

    // TODO: 실습용으로 header 추가할 것
    @PostMapping("")
    suspend fun createTodo(@RequestBody todoDto: TodoDto): TodoDto = todoClient.createTodo(todoDto)
}