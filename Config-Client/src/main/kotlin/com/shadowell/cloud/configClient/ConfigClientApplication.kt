package com.shadowell.cloud.configClient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@EnableEurekaClient
open class ConfigClientApplication

fun main(args: Array<String>) {
    runApplication<ConfigClientApplication>(*args)
}
