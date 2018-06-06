package com.shadowell.cloud.Controller


import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

class ConfigController {

    @Value("{foo}")
    private var foo: String = ""

    @RequestMapping(value = ["/foo"],method = [RequestMethod.GET])
    fun getConfig(): String{
        return foo
    }
}