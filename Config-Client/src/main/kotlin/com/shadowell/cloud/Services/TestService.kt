package com.shadowell.cloud.Services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sun.rmi.runtime.Log
import java.util.logging.LogManager

@Service
class TestService (@Autowired private testDao: TestDao){

    var logger: Logger = LogManager.getLogger(this.javaClass)

    fun insertTestData(): TestDataModel {

    }
}