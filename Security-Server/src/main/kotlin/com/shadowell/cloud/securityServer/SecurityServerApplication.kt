package com.shadowell.cloud.securityServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
//@EnableEurekaClient
class SecurityServerApplication

fun main(args: Array<String>) {
    runApplication<SecurityServerApplication>(*args)
}
