package com.shadowell.cloud.configClient.Controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class ConfigController {

    @Value(value = "\${name}")
    private val name: String = ""

    @RequestMapping(value = ["/name"],method = [RequestMethod.GET])
    fun getConfig(): String{
        return "----------------$name"
    }

}