package com.shadowell.cloud.Controller


import com.shadowell.cloud.Config.ServerConfig
import com.shadowell.cloud.Services.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class ConfigController {

//    @Value(value = "${name}")xx
//    private val name: String = ""
    @Autowired
    lateinit var serverConfig: ServerConfig

    @Autowired
    lateinit var testService: TestService

    @RequestMapping(value = ["/name"],method = [RequestMethod.GET])
    fun getConfig(): String{
        return serverConfig.name
    }

    @RequestMapping(value = ["/test"],method = [RequestMethod.POST])
    fun insertTestData(): String{
        return testService.insertTestData()
    }

    @RequestMapping(value = ["/test/{testId}"],method = [RequestMethod.GET])
    fun getTestData(): String{
        return testService.getTestData(testId)
    }
}