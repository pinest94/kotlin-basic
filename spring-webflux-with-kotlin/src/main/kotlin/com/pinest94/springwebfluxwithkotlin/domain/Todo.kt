package com.pinest94.springwebfluxwithkotlin.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.*

@Table("todos")
data class Todo(
    @Id
    var id: Long? = null,
    var content: String? = null,
    var done: Boolean = false,
    var createdAt: Instant = Instant.now(),
    var modifiedAt: Instant = createdAt
)