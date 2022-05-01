package com.pinest94.springrediswithkotlin.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "todo")
data class Todo(
    @Id val id: Long? = null,
    val content: String?,
    val done: Int?,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?,
) {
    constructor(content: String, done: Int) : this(null, content, done, null, null)
    constructor() : this(null, null, null, null, null)
}
