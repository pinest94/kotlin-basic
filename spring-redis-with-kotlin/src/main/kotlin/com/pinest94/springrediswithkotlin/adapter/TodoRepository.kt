package com.pinest94.springrediswithkotlin.adapter

import com.pinest94.springrediswithkotlin.domain.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {
    override fun findById(id: Long): Optional<Todo>
}
