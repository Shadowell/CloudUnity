package com.shadowell.cloud.Model

import org.springframework.context.annotation.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import javax.persistence.*

@Entity
class User: UserDetails, Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long = 0

    @Column(nullable = false, unique = true)
    private var usrname: String = ""

    @Column(nullable = false)
    private var password: String = ""

    val authority: List<Role> = listOf()

    override fun getUsername(): String {
        return username
    }

    fun getId(): Long {
        return id
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun setUsername(username: String) {
        this.usrname = usrname
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}