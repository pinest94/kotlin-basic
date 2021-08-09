package com.pinest94.springwebfluxwithkotlin.repository

import com.pinest94.springwebfluxwithkotlin.domain.Todo
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TodoRepository : ReactiveCrudRepository<Todo, Long>{
}