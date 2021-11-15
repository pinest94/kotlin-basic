package com.pinest94.springwebfluxwithkotlin.router

import com.pinest94.springwebfluxwithkotlin.domain.Todo
import com.pinest94.springwebfluxwithkotlin.handler.TodoHandler
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Message API", description = "message controller")
@RestController
@CrossOrigin(allowCredentials = "false", originPatterns = ["*"])
class TodoController(
    private val todoHandler: TodoHandler
) {

    @Operation(summary = "joinInfos", description = "get ajoinInfos")
    @PostMapping("/joinInfos")
    suspend fun create(
        @RequestBody todo: Todo
    ): Todo {
        return todoHandler.save2(todo)
    }
}
