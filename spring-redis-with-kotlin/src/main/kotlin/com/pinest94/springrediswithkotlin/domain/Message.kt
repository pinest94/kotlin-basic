package com.pinest94.springrediswithkotlin.domain

data class Message(
    val id: String,
    val templateId: Int,
    val params: Map<String, Any>,
    val exposedAt: Long,
    val createdAt: Long,
    val fedAt: Long,
    val expiredAt: Long
)