package com.shadowell.cloud.wechat.controller

import com.shadowell.cloud.wechat.model.CustomerModel
import com.shadowell.cloud.wechat.utils.Result
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.net.Inet4Address


@RestController
@RequestMapping(value = ["/customer"])
class CustomerController (@Autowired private var customerService: CustomerService) {

    private val logger: Logger = LogManager.getLogger(CustomerController::class)

    private val ip = Inet4Address.getLocalHost().hostAddress
    private val hostName = Inet4Address.getLocalHost().hostName
    //private val port = ServiceInfoConfig().getServicePort()

    @RequestMapping(value = [""],method = [RequestMethod.POST])
    @ResponseBody
    fun addCustomer(@RequestBody customerModel: CustomerModel): Result {
        logger.info("Add customer use customer-service from $hostName: $ip: ")
        return customerService.addCustomer(customerModel)
    }

    @RequestMapping(value = ["/{id}"],method = [RequestMethod.GET])
    @ResponseBody
    fun getCustomerById(@PathVariable id: String): CustomerModel {
        logger.info("Get customer by id $id".format(id))
        return customerService.getCustomerById(id)
    }

    @RequestMapping(value = ["/{id}"],method = [RequestMethod.DELETE])
    @ResponseBody
    fun deleteConsumer(@PathVariable id: String): Result {
        logger.info("Delete customer by id $id".format(id))
        return customerService.deleteConsumer(id)
    }
}
