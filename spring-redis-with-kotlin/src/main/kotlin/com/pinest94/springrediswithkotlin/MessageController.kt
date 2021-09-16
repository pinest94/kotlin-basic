package com.pinest94.springrediswithkotlin

import com.pinest94.springrediswithkotlin.adapter.TemplateAdapter
import com.pinest94.springrediswithkotlin.config.RedisProperties
import com.pinest94.springrediswithkotlin.domain.Message
import com.pinest94.springrediswithkotlin.domain.MessageService
import com.pinest94.springrediswithkotlin.domain.Template
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableConfigurationProperties(RedisProperties::class)
class MessageController(
    private val messageService: MessageService,
    private val templateAdapter: TemplateAdapter
) {

    @GetMapping("/messages")
    suspend fun getMessages(
        @RequestHeader("X-Line-Mid") mid: String
    ): List<Message> {
        return messageService.getMessage(mid)
    }

    @DeleteMapping("/messages")
    suspend fun removeMessages(
        @RequestHeader("X-Line-Mid") mid: String
    ) {
        messageService.removeWithExpired(mid)
    }

    @GetMapping("/templates")
    suspend fun getTemplates(
        @RequestHeader("Template-Id") templateId: String
    ): Template {
        return templateAdapter.get(templateId)
    }
}