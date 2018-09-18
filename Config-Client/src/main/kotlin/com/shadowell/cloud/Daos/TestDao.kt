package com.shadowell.cloud.Daos

import com.shadowell.cloud.Models.TestDataModel
import org.springframework.data.jpa.repository.JpaRepository

interface TestDao: JpaRepository <TestDataModel, String> {

    @Query(value = "select testData from TestDataModel testData where id = ?1")
    fun getTestData(testId: String): TestDataModel ?
}