package com.shadowell.cloud.productService.Config

import com.fasterxml.jackson.core.JsonProcessingException
import java.io.IOException
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Primary
import com.fasterxml.jackson.databind.JsonSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class JacksonConfig {
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper::class)
    fun jacksonObjectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
        val objectMapper = builder.createXmlMapper(false).build<ObjectMapper>()
        val serializerProvider = objectMapper.serializerProvider
        serializerProvider.setNullValueSerializer(object : JsonSerializer<Any>() {
            @Throws(IOException::class, JsonProcessingException::class)
            override fun serialize(o: Any, jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider) {
                jsonGenerator.writeString("")
            }
        })
        return objectMapper
    }
}