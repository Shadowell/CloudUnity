//package com.shadowell.cloud.configClient.Daos
//
//import com.shadowell.cloud.configClient.Models.TestDataModel
//import org.springframework.data.jpa.repository.JpaRepository
//import org.springframework.data.jpa.repository.Query
//
//interface TestDao: JpaRepository <TestDataModel, String> {
//
//    @Query(value = "select testData from TestDataModel testData where id = ?1")
//    fun getTestData(testId: String): TestDataModel?
//}