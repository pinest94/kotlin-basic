package com.pinest94.springwebfluxwithkotlin.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment

@Configuration
@Profile(value = ["local", "alpha", "beta", "rc"])
class SwaggerConfiguration(
    private val environment: Environment
) {
    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(Info()
                .title("My dashboard - ${environment.activeProfiles[0]}")
                .description("My dashboard documentation"))

    }
}