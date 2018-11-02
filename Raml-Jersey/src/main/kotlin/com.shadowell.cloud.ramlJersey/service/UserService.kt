package com.shadowell.cloud.ramlJersey.service

import com.external.api.Auser
import com.external.api.ResponseEntityImpl
import com.external.api.UserImpl
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Component


@Component
class UserService {

    companion object {
        private val logger: Logger = LogManager.getLogger(UserService::class)
    }

    fun getAuser(): Auser.GetAuserResponse{
        val user = UserImpl()
        user.age = 28
        user.name = "test"
        user.telephone = 1000000
        logger.info("Get a user: $user")
        return Auser.GetAuserResponse.respond200WithApplicationJson(user)
    }

    fun addAuser(): Auser.PostAuserResponse{
        val response = ResponseEntityImpl()
        response.code = 200
        response.message = "Add user success"
        logger.error(response.message)
        return Auser.PostAuserResponse.respond200WithApplicationJson(response)
    }
}