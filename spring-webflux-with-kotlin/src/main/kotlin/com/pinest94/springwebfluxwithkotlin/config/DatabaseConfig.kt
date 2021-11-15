package com.pinest94.springwebfluxwithkotlin.config

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration
import dev.miku.r2dbc.mysql.MySqlConnectionFactory
import io.r2dbc.pool.ConnectionPool
import io.r2dbc.pool.ConnectionPoolConfiguration
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ValidationDepth
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import java.time.Duration
import java.time.ZoneId

@Configuration
class DatabaseConfig(
    private val dataSource: DataSourceProperty
) : AbstractR2dbcConfiguration() {

    @Bean
    override fun connectionFactory(): ConnectionFactory {
        val mysqlConnectionConfiguration = MySqlConnectionConfiguration.builder()
            .host(dataSource.host)
            .user(dataSource.user)
            .port(dataSource.port)
            .password(dataSource.password)
            .database(dataSource.database)
            .serverZoneId(ZoneId.of("Asia/Tokyo"))
            .connectTimeout(Duration.ofSeconds(3)).build()

        val connectionFactory = MySqlConnectionFactory.from(mysqlConnectionConfiguration)
        val configuration = ConnectionPoolConfiguration.builder(connectionFactory)
            .acquireRetry(1)
            .backgroundEvictionInterval(Duration.ZERO)
            .initialSize(3)
            .maxSize(3)
            .maxLifeTime(Duration.ZERO)
            .maxIdleTime(Duration.ofMinutes(30))
            .maxAcquireTime(Duration.ZERO)
            .maxCreateConnectionTime(Duration.ZERO)
            .registerJmx(false)
            .validationDepth(ValidationDepth.LOCAL)
            .validationQuery("SELECT 1")
            .build()

        return ConnectionPool(configuration)
    }
}