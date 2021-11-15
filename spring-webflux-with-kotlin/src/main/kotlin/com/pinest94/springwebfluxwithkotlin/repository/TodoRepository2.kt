package com.pinest94.springwebfluxwithkotlin.repository

import com.pinest94.springwebfluxwithkotlin.domain.Todo
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TodoRepository2 : CoroutineCrudRepository<Todo, Long> {
}