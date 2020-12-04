package com.shadowell.cloud.wechat.outerInterface

import com.shadowell.cloud.wechat.model.ProductModel
import com.shadowell.cloud.wechat.utils.Result
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
