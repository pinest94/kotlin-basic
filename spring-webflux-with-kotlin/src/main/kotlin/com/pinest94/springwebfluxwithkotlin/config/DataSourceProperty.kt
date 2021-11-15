package com.pinest94.springwebfluxwithkotlin.config

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.context.annotation.Configuration

// nClavis inlineCipherText to Object
@Configuration
data class DataSourceProperty(
    val host: String = "localhost",
    val port: Int = 3306,
    val database: String = "todo",
    val user: String = "todo-manager",
    val password: String = "rla13628747^^todo",
)
