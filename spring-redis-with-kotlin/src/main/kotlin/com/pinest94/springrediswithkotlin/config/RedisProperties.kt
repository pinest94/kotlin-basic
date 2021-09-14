package com.pinest94.springrediswithkotlin.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "redis")
data class RedisProperties(

    val master: String,
    val password: String,
    val cluster: RedisClusterProperties = RedisClusterProperties(),
    val client: RedisClientProperties = RedisClientProperties()
)

data class RedisClusterProperties(
    val maxRedirects: Int = 3,
    val commandTimeout: Long = 1000L,
    val password: String? = null,
)

data class RedisClientProperties(
    val timeout: Long = 1000L,
    val socketTimeout: Long = 1000L
)



