//package com.shadowell.cloud.productService.Daos
//
//import com.shadowell.cloud.productService.Models.ProductEntity
//import org.springframework.data.jpa.repository.JpaRepository
//import org.springframework.data.jpa.repository.Query
//import java.util.*
//
//interface ProductDao: JpaRepository <ProductEntity, String> {
//
//    @Query(value = "select product from ProductEntity product where id = ?1")
//    fun findProductById(id: String): Optional<ProductEntity>
//
//    @Query(value = "select product from ProductEntity product where productName = ?1")
//    fun findProductByName(name: String): Optional<ProductEntity>
//}