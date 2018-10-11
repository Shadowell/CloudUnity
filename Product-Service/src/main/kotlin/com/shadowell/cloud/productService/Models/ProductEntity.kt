package com.shadowell.cloud.productService.Models

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Id

class ProductEntity constructor(): Serializable {

    @Id
    @Column
    var id: String = ""

    @Column
    var productName: String = ""

    @Column
    var productCount: Int = 0
}