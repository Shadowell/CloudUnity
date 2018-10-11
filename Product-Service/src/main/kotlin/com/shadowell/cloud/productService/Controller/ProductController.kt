package com.shadowell.cloud.productService.Controller


import com.shadowell.cloud.productService.Models.ProductEntity
import com.shadowell.cloud.productService.Models.ProductModel
import com.shadowell.cloud.productService.Services.ProductService
import com.shadowell.cloud.productService.Utils.Result
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping(value = ["/product"])
class ProductController @Autowired constructor(private var productService: ProductService) {

    private val logger: Logger = LogManager.getLogger(ProductController::class)

    @RequestMapping(value = ["/product"],method = [RequestMethod.POST])
    @ResponseBody
    fun addProduct(@RequestBody productModel: ProductModel): Result {
        logger.info("Add product")
        return productService.addProduct(productModel)
    }

//    @RequestMapping(value = ["/product/{id}"],method = [RequestMethod.GET])
//    @ResponseBody
//    fun getProductById(@PathVariable id: String): Optional<ProductEntity> {
//        logger.info("Get product by id $id".format(id))
//        return productService.getProductById(id)
//    }

    @RequestMapping(value = ["/product/{id}"],method = [RequestMethod.DELETE])
    @ResponseBody
    fun deleteProduct(@PathVariable id: String): Result {
        logger.info("Delete product by id $id".format(id))
        return productService.deleteProduct(id)
    }
}