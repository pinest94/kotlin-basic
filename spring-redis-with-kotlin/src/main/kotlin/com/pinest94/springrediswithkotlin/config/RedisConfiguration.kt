package com.pinest94.springrediswithkotlin.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.pinest94.springrediswithkotlin.domain.Message
import com.pinest94.springrediswithkotlin.domain.Template
import com.pinest94.springrediswithkotlin.util.objectMapper
import io.lettuce.core.SocketOptions
import io.lettuce.core.TimeoutOptions
import io.lettuce.core.cluster.ClusterClientOptions
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.GenericToStringSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.RedisSerializer
import java.time.Duration

@EnableConfigurationProperties(RedisProperties::class)
@Configuration
class RedisConfiguration(
    private val redisProperties: RedisProperties
) {

    @Bean
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        val redisConfiguration = RedisStandaloneConfiguration()

        redisConfiguration.hostName = redisProperties.master
        redisConfiguration.password = RedisPassword.of(redisProperties.password)

        val clientConfiguration = LettuceClientConfiguration
            .builder()
            .commandTimeout(Duration.ofMillis(redisProperties.cluster.commandTimeout))
            .clientOptions(
                ClusterClientOptions
                    .builder()
                    .validateClusterNodeMembership(false)
                    .autoReconnect(true)
                    .socketOptions(
                        SocketOptions.builder()
                            .connectTimeout(Duration.ofMillis(redisProperties.client.socketTimeout))
                            .tcpNoDelay(true)
                            .keepAlive(true)
                            .build()
                    )
                    .timeoutOptions(TimeoutOptions.enabled(Duration.ofMillis(redisProperties.client.timeout)))
                    .topologyRefreshOptions(topologyRefreshOptions())
                    .build()
            )
            .build()
        return LettuceConnectionFactory(redisConfiguration, clientConfiguration)
    }

    private fun topologyRefreshOptions(): ClusterTopologyRefreshOptions {
        return ClusterTopologyRefreshOptions
            .builder()
            .enablePeriodicRefresh(Duration.ofSeconds(30))
            .enableAllAdaptiveRefreshTriggers()
            .build()
    }

    @Suppress("UNCHECKED_CAST")
    private inline fun <reified T> getReactiveRedisTemplate(
        reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory,
        objectMapper: ObjectMapper,
    ): ReactiveRedisTemplate<String, T> {
        val serializer: RedisSerializer<T> = when (T::class) {
            Boolean::class, Byte::class, Char::class,
            Double::class, Float::class, Int::class,
            Long::class, Short::class -> GenericToStringSerializer(T::class.java)
            String::class -> RedisSerializer.string() as RedisSerializer<T>
            else -> {
                val serializer = Jackson2JsonRedisSerializer(T::class.java)
                serializer.setObjectMapper(objectMapper)
                serializer
            }
        }

        val serializationContext = RedisSerializationContext
            .newSerializationContext<String, T>(RedisSerializer.string())
            .value(serializer)
            .build()

        return ReactiveRedisTemplate<String, T>(reactiveRedisConnectionFactory, serializationContext)
    }

    @Bean
    fun messageReactiveRedisTemplate(
        reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory
    ): ReactiveRedisTemplate<String, Message> {
        return getReactiveRedisTemplate(reactiveRedisConnectionFactory, objectMapper)
    }

    @Bean
    fun templateReactiveRedisTemplate(
        reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory
    ): ReactiveRedisTemplate<String, Template> {
        return getReactiveRedisTemplate(reactiveRedisConnectionFactory, objectMapper)
    }
}