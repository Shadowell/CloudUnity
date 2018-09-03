package com.shadowell.cloud.Daos

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration

interface TestDao: JpaRepository <TestDataModel, String> {

    @Query(value = "select testData from TestDataModel testData where id = ?1")
    fun getTestData(testId: String): TestDataModel ?
}