package com.pinest94.springwebfluxwithkotlin.handler

import com.pinest94.springwebfluxwithkotlin.domain.Todo
import com.pinest94.springwebfluxwithkotlin.repository.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import java.net.URI
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collectors.toList

@Component
class TodoHandler(private val repo: TodoRepository) {
    fun getAll(req: ServerRequest): Mono<ServerResponse> =
        repo.findAll().filter(Objects::nonNull)
            .collect(toList())
            .flatMap { ok().body(Mono.just(it)) }

    fun getById(req: ServerRequest): Mono<ServerResponse> =
        repo.findById(req.pathVariable("id").toLong())
            .flatMap { ok().body(Mono.just(it)) }
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun save(req: ServerRequest): Mono<ServerResponse> =
        repo.saveAll(req.bodyToMono(Todo::class.java))
            .flatMap { ok().body(Mono.just(it)) }
            .next()

    fun done(req: ServerRequest): Mono<ServerResponse> =
        repo.findById(req.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { todo ->
                todo.done = true
                todo.modifiedAt = LocalDateTime.now()
                repo.save(todo)
            }
            .flatMap { it?.let { ok().build() } }

    fun delete(req: ServerRequest): Mono<ServerResponse> =
        repo.findById(req.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { todo -> ok().build(repo.deleteById(todo.id!!)) }
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())
}
