package com.pinest94.springrediswithkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class SpringRedisWithKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringRedisWithKotlinApplication>(*args)
}
