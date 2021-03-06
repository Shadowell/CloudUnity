package com.shadowell.cloud.wechat.service

import com.netflix.hystrix.contrib.javanica.command.AsyncResult
import com.shadowell.cloud.wechat.model.CustomerModel
import com.shadowell.cloud.wechat.outerInterface.ProductServiceInterface
import com.shadowell.cloud.wechat.utils.MessageDigestUtil
import com.shadowell.cloud.wechat.utils.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.web.client.AsyncRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.util.concurrent.ListenableFutureCallback

@Service
class WechatSecurityService @Autowired constructor(private var productServiceInterface: ProductServiceInterface) {

    private val logger: Logger = LogManager.getLogger(WechatSecurityService::class)

    fun verifySignature(signature: String, timestamp: Long, nonce: String, echostr: String): String {
        val token: String = "P511GW5"
        val signatureArray = listOf(token, timestamp.toString(), nonce)
        signatureArray.sorted()
        val shaString = MessageDigestUtil.sha1(signatureArray.toString())
        if (signature == shaString) {
            return echostr
        } else {
            return ""
        }
    }

    fun getCustomerById(id: String): CustomerModel {
        val product = productServiceInterface.getProductById(id)
        logger.info(product.toString())
        logger.info(product.id,product.productName,product.productCount)
        when(id) {
            product.id -> return CustomerModel("1", "xiaoming", 10, "13000000000")
            else -> return CustomerModel("2", "xiaohong", 12, "18000000000")
        }
    }


    /**
     * 异步调用
     */
    fun deleteConsumer(id: String): Result {
        val url: String = String.format("http://localhost:8400/product/%s",id)
        val asyncTemplate = AsyncRestTemplate()
        //调用完后立即返回（没有阻塞）
        val forEntity = asyncTemplate.getForEntity(url, String::class.java)
        logger.info("当前线程：name = %s".format(Thread.currentThread().name))
        logger.info("等待返回...,Time = %s".format(System.currentTimeMillis()))
        //异步调用后的回调函数
        forEntity.addCallback(object : ListenableFutureCallback<ResponseEntity<String>> {
            //调用失败
            override fun onFailure(ex: Throwable) {
                logger.info("失败线程：name = %s".format(Thread.currentThread().name))
                logger.error("=====rest response failure======,Time = %s".format(System.currentTimeMillis()))
            }

            //调用成功
            override fun onSuccess(result: ResponseEntity<String>?) {
                logger.info("成功线程：name = %s".format(Thread.currentThread().name))
                logger.info("=====async rest response success======, result = " + result!!.body!!)
            }
        })
        return Result.FAIL
    }

}
