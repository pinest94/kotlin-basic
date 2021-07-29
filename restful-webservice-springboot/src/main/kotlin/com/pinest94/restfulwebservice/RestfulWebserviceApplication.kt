package com.pinest94.restfulwebservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class RestfulWebserviceApplication

fun main(args: Array<String>) {
    runApplication<RestfulWebserviceApplication>(*args)
}

@RestController
class MessageResource(val service: MessageService) {

    @GetMapping("/messages")
    fun index(): List<Message> = service.findMessages()

    @PostMapping("/messages")
    fun post(@RequestBody message: Message) {
        service.post(message)
    }
}
