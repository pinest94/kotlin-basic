package com.pinest94.springwebfluxwithkotlin.repository

import com.pinest94.springwebfluxwithkotlin.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long>{
}