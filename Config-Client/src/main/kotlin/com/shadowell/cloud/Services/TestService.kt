package com.shadowell.cloud.Services

import com.shadowell.cloud.Daos.TestDao
import com.shadowell.cloud.Models.TestDataModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Service
class TestService (@Autowired private testDao: TestDao){

    private val logger: Logger = LogManager.getLogger(this.javaClass)

    fun insertTestData(): TestDataModel {

    }
}