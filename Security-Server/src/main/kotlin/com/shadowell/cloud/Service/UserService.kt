package com.shadowell.cloud.Service

import com.shadowell.cloud.Dao.UserDao
import com.shadowell.cloud.Model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.util.*

class UserService(@Autowired var userDao: UserDao): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userDao.findByUsername(username = username)
        if (user == null) {
            throw UsernameNotFoundException("Username $username not found".format(username))
        }
        return user
    }
}