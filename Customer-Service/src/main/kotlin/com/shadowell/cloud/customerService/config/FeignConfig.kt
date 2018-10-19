package com.shadowell.cloud.customerService.config


import com.shadowell.cloud.customerService.service.CustomerService
import feign.Retryer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.cloud.openfeign.FeignClientProperties
import org.springframework.cloud.openfeign.FeignClientsConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Configuration
open class FeignConfig {

    private val logger: Logger = LogManager.getLogger(FeignConfig::class)

    @Bean
    fun feignRetryer(): Retryer {
        logger.info("Retry to call")
        return Retryer.Default(100,TimeUnit.SECONDS.toMillis(1),2)
    }
}