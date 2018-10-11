package com.shadowell.cloud.Dao

import com.shadowell.cloud.Model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface UserDao: JpaRepository<User, Long> {
    fun findByUsername(username: String): UserDetails ?
}