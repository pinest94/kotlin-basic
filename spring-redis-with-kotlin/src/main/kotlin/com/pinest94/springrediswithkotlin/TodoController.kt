package com.pinest94.springrediswithkotlin

import com.pinest94.springrediswithkotlin.adapter.TodoRepository
import com.pinest94.springrediswithkotlin.config.RedisProperties
import com.pinest94.springrediswithkotlin.domain.Todo
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TodoController(private val todoAdapter: TodoRepository) {

    @GetMapping("/todo/{id}")
    fun getMessages(
        @PathVariable id: Long
    ): Todo {
        return todoAdapter.findById(id).get()
    }
}