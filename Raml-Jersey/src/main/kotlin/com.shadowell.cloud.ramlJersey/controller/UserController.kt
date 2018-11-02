package com.shadowell.cloud.ramlJersey.controller

import com.external.api.Auser
import com.shadowell.cloud.ramlJersey.service.UserService
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces



@Component
class UserController @Autowired constructor(private val userService: UserService) : Auser{

    companion object {

        private val logger: Logger = LogManager.getLogger(UserController::class)
    }

    //@GET
    //@Produces("application/json")
    override fun getAuser(): Auser.GetAuserResponse {
        return userService.getAuser()
    }

//    @POST
//    @Produces("application/json")
    override fun postAuser(): Auser.PostAuserResponse {
        return userService.addAuser()
    }

    //不能使用Spring MVC注解
//    @RequestMapping(value = [""],method = [RequestMethod.POST])
//    @Produces("application/json")
//    override fun postAuser(): Auser.PostAuserResponse {
//        return userService.addAuser()
//    }
}