package com.shadowell.cloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class CloudApplication

fun main(args: Array<String>) {
    runApplication<CloudApplication>(*args)
}
