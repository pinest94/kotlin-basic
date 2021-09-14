package com.pinest94.springrediswithkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringRedisWithKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringRedisWithKotlinApplication>(*args)
}
