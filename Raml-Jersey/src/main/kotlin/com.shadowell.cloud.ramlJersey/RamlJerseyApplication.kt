package com.shadowell.cloud.ramlJersey

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.convert.ApplicationConversionService.configure
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer




//@SpringBootApplication
//class RamlJerseyApplication
//fun main(args: Array<String>) {
//    runApplication<RamlJerseyApplication>(*args)
//}

@SpringBootApplication
open class RamlJerseyApplication : SpringBootServletInitializer() {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            RamlJerseyApplication()
                    .configure(SpringApplicationBuilder(RamlJerseyApplication::class.java))
                    .run(*args)
        }
    }

}