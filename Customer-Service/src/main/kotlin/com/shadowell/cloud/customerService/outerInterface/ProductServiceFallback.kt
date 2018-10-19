package com.shadowell.cloud.customerService.outerInterface

import com.shadowell.cloud.customerService.model.ProductModel
import com.shadowell.cloud.customerService.utils.Result
import org.springframework.stereotype.Component

@Component
class ProductServiceFallback: ProductServiceInterface {

    override fun getProductById(id: String): ProductModel {
        return ProductModel("unknow","unknow",0)
        //throw Exception("Error occur during invoke Api:getProductById")
    }

    override fun deleteProduct(id: String): Result {
        throw Exception("Error occur during invoke Api:deleteProduct")
    }
}
