package com.pinest94.retrofitdemo.controller

import com.pinest94.retrofitdemo.retrofit.TodoClient
import com.pinest94.retrofitdemo.retrofit.dto.TodoDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("todos")
class TodoController(
    private val todoClient: TodoClient
) {

    @GetMapping("/{id}")
    suspend fun getTodo(@PathVariable("id") id: Long): TodoDto = todoClient.getTodo(id)

    @GetMapping("")
    suspend fun getTodoAll(): List<TodoDto> = todoClient.getTodoAll()
}