package com.shadowell.cloud.proxyServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
//@EnableEurekaClient
class ProxyServerApplication

fun main(args: Array<String>) {
    runApplication<ProxyServerApplication>(*args)
}
