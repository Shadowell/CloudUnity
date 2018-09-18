package com.shadowell.cloud.Models

data class TestDataModel (
        @Id
        @Column
        var id: String = "",

        @Column
        var name: String = "",

        @Column
        var age: String = ""
)