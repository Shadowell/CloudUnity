package com.shadowell.cloud.Model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
class Role: GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long = 0

    @Column(nullable = false)
    private var name: String = ""

    fun getId(): Long {
        return id
    }

    fun setId(id: Long) {
        this.id = id
    }

    override fun getAuthority(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }
    override fun toString(): String {
        return name

    }
}