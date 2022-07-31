package ru.viktorxdev.easypayment.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class ObjectMapperConfiguration {

    @Bean
    fun objectMapperBuilder() = Jackson2ObjectMapperBuilder()
        .apply {
            serializationInclusion(JsonInclude.Include.NON_NULL)
            featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            modules(
                listOf(
                    KotlinModule.Builder()
                        .configure(KotlinFeature.NullToEmptyCollection, true)
                        .configure(KotlinFeature.NullToEmptyMap, true)
                        .configure(KotlinFeature.NullIsSameAsDefault, true)
                        .build(),
                    JavaTimeModule()
                )
            )
        }
}