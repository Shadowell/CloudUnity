package com.shadowell.cloud.productService.Controller


//import com.shadowell.cloud.productService.Models.ProductEntity
import com.shadowell.cloud.productService.Config.ServiceInfoConfig
import com.shadowell.cloud.productService.Models.ProductModel
import com.shadowell.cloud.productService.Services.ProductService
import com.shadowell.cloud.productService.Utils.Result
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.net.Inet4Address



@RestController
@RequestMapping(value = ["/product"])
class ProductController (@Autowired var productService: ProductService) {

    private val logger: Logger = LogManager.getLogger(ProductController::class)

    private val ip = Inet4Address.getLocalHost().hostAddress
    private val hostName = Inet4Address.getLocalHost().hostName
    //private val port = ServiceInfoConfig().getServicePort()

    @RequestMapping(value = [""],method = [RequestMethod.POST])
    @ResponseBody
    fun addProduct(@RequestBody productModel: ProductModel): Result {
        logger.info("Add product use product-service from $hostName: $ip: ")
        return productService.addProduct(productModel)
    }

    @RequestMapping(value = ["/{id}"],method = [RequestMethod.GET])
    @ResponseBody
    fun getProductById(@PathVariable id: String): ProductModel {
        logger.info("Get product by id $id".format(id))
        return productService.getProductById(id)
    }

    @RequestMapping(value = ["/{id}"],method = [RequestMethod.DELETE])
    @ResponseBody
    fun deleteProduct(@PathVariable id: String): Result {
        logger.info("Delete product by id $id".format(id))
        return productService.deleteProduct(id)
    }
}