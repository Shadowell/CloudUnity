package com.shadowell.cloud.ramlJersey.config

import com.external.api.Auser
import com.shadowell.cloud.ramlJersey.controller.UserController

import com.shadowell.cloud.ramlJersey.service.UserService
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component
import javax.ws.rs.ApplicationPath

@Component
class JerseyConfig : ResourceConfig() {
    init {
        //不要用UserController::class,此获取到的是KClass
        register(UserController::class.java)
        //packages("com.shadowell.cloud.ramlJersey.controller")
    }
}