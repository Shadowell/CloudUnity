package com.shadowell.cloud.productService.Services

//import com.shadowell.cloud.productService.Daos.ProductDao
//import com.shadowell.cloud.productService.Models.ProductEntity
import com.shadowell.cloud.productService.Models.ProductModel
import com.shadowell.cloud.productService.Utils.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*

@Service
class ProductService @Autowired constructor(/**private var productDao: ProductDao*/){

    private val logger: Logger = LogManager.getLogger(ProductService::class)

    fun addProduct(productModel: ProductModel): Result {
//        val target = productDao.findProductByName(productModel.productName)
//        if (!target.isPresent) {
//            return Result.NOT_FOUNT
//        }
//        val newProduct = ProductEntity()
//        newProduct.id = UUID.randomUUID().toString()
//        newProduct.productName = productModel.productName
//        newProduct.productCount = productModel.productCount
//        productDao.save(newProduct)
        return Result.SUCCESS
    }

//    fun getProductById(id: String): Optional<ProductEntity> {
//        return productDao.findProductById(id)
//        return Optional<ProductEntity>()
//    }

    fun deleteProduct(id: String): Result {
//        val target = productDao.findProductById(id)
//        if (!target.isPresent) {
//            return Result.NOT_FOUNT
//        }
//        productDao.deleteById(id)
        return Result.SUCCESS
    }

}