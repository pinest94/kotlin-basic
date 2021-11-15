package com.pinest94.springrediswithkotlin

import com.pinest94.springrediswithkotlin.adapter.MessageAdapter
import com.pinest94.springrediswithkotlin.config.RedisProperties
import com.pinest94.springrediswithkotlin.domain.Message
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableConfigurationProperties(RedisProperties::class)
class MessageController(private val messageAdapter: MessageAdapter) {

    @GetMapping("/messages")
    suspend fun getMessages(
        @RequestHeader("X-Line-Mid") mid: String
    ): List<Message> {
       return messageAdapter.get(mid)
    }
}