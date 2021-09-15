package com.pinest94.springrediswithkotlin.adapter

import com.pinest94.springrediswithkotlin.domain.Message
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.domain.Range
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Component

@Component
class MessageAdapter(
    private val reactiveRedisTemplate: ReactiveRedisTemplate<String, Message>
) {
    private val key: String = "messages"

    private fun key(mid: String): String {
        return "$key:$mid"
    }

    suspend fun get(mid: String): List<Message> {
        val result =  reactiveRedisTemplate.opsForZSet()
            .range(key(mid), Range.open(0, -1))
            .asFlow()
            .toList()

        println(result.toString())
        return result
    }
}