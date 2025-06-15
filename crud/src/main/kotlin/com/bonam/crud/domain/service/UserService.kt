package com.bonam.crud.domain.service

import com.bonam.crud.domain.model.User
import com.bonam.crud.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun createUser(user: User): User {
        if (userRepository.findByUsername(user.username) != null) {
            throw IllegalArgumentException("User already exists")
        }
        val hashed = passwordEncoder.encode(user.password)
        val userToSave = user.copy(password = hashed)
        return userRepository.save(userToSave)
    }

    fun findByUsername(username: String): User? = userRepository.findByUsername(username)

    fun getAllUsers(): List<User> = userRepository.findAll()
}
