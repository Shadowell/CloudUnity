package com.shadowell.cloud.wechat.controller

import com.shadowell.cloud.wechat.model.CustomerModel
import com.shadowell.cloud.wechat.service.WechatSecurityService
import com.shadowell.cloud.wechat.utils.Result
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.net.Inet4Address
import java.util.concurrent.Future


@RestController
@RequestMapping(value = ["/wechat"])
class WechatController (@Autowired private var wechatSecurityService: WechatSecurityService) {

    private val logger: Logger = LogManager.getLogger(WechatController::class)

    private val ip = Inet4Address.getLocalHost().hostAddress
    private val hostName = Inet4Address.getLocalHost().hostName
    //private val port = ServiceInfoConfig().getServicePort()

    @RequestMapping(value = [""],method = [RequestMethod.GET])
    @ResponseBody
    fun verifySignature(@PathVariable signature: String, timestamp: Long,
                        nonce: String, echostr: String): String {
        val message = "Verify token success"
        logger.info(message)
        return wechatSecurityService.verifySignature(signature, timestamp, nonce, echostr);
    }
}
