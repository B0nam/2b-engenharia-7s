package com.bonam.crud.domain.repository

import com.bonam.crud.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}
