package com.pinest94.springrediswithkotlin.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.GenericToStringSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.RedisSerializer

class RedisTemplateProvider(
    private val connectionFactory: ReactiveRedisConnectionFactory,
    private val objectMapper: ObjectMapper
) {
    private val serializers: MutableMap<String, RedisSerializer<*>> = HashMap()

    @Suppress("UNCHECKED_CAST")
    fun <T> createRedisTemplate(clazz: Class<T>): ReactiveRedisTemplate<String, T> {
        val serializer: RedisSerializer<T> =
            serializers[clazz.name]?.let { it as RedisSerializer<T> } ?: when (clazz) {
                Boolean::class, Byte::class, Char::class,
                Double::class, Float::class, Int::class,
                Long::class, Short::class -> GenericToStringSerializer(clazz)
                String::class -> RedisSerializer.string() as RedisSerializer<T>
                else -> {
                    val s = Jackson2JsonRedisSerializer(clazz)
                    s.setObjectMapper(objectMapper)
                    s
                }
            }
        serializers[clazz.name] = serializer

        val serializationContext = RedisSerializationContext
            .newSerializationContext<String, T>()
            .key(RedisSerializer.string())
            .hashKey(RedisSerializer.string())
            .value(serializer)
            .hashValue(serializer)
            .build()
        return ReactiveRedisTemplate<String, T>(connectionFactory, serializationContext)
    }
}