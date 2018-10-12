package com.shadowell.cloud.zuulServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy


@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
class ZuulServerApplication

fun main(args: Array<String>) {
    runApplication<ZuulServerApplication>(*args)
}
