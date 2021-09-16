package com.pinest94.springrediswithkotlin.adapter

import com.pinest94.springrediswithkotlin.domain.Message
import com.pinest94.springrediswithkotlin.util.getCurrentTime
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.domain.Range
import org.springframework.data.redis.core.ReactiveZSetOperations
import org.springframework.data.redis.core.removeRangeByScoreAndAwait
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneOffset

@Component
class MessageAdapter(
    private val messageTemplate: ReactiveZSetOperations<String, Message>
) {
    private val key: String = "messages"

    private fun key(mid: String): String {
        return "$key:$mid"
    }

    suspend fun get(mid: String): List<Message> {

        return messageTemplate
            .range(key(mid), Range.open(0, -1))
            .asFlow()
            .toList()
    }

    suspend fun remove(mid: String) {
        messageTemplate.removeRangeByScoreAndAwait(key(mid),
            Range.open(0.0, getCurrentTime().toDouble()))
    }
}