package com.shadowell.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class SecurityServerApplication

fun main(args: Array<String>) {
    runApplication<SecurityServerApplication>(*args)
}
