package com.shadowell.cloud.customerService.outerInterface

import com.shadowell.cloud.customerService.config.FeignConfig
import com.shadowell.cloud.customerService.model.ProductModel
import com.shadowell.cloud.customerService.utils.Result
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "product-service",configuration = [FeignConfig::class],fallback = ProductServiceFallback::class)
interface ProductServiceInterface {

    @GetMapping(value = ["/product/{id}"])
    fun getProductById(@PathVariable id: String): ProductModel

    @DeleteMapping(value = ["/product/{id}"])
    fun deleteProduct(id: String): Result
}