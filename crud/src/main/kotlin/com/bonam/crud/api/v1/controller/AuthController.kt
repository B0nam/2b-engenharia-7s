package com.bonam.crud.api.v1.controller

import com.bonam.crud.domain.model.User
import com.bonam.crud.domain.repository.UserRepository
import com.bonam.crud.infraestructure.security.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil
) {
    private val passwordEncoder = BCryptPasswordEncoder()

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<String> {
        if (userRepository.findByUsername(user.username) != null) {
            return ResponseEntity.badRequest().body("User already exists")
        }
        val hashed = passwordEncoder.encode(user.password)
        userRepository.save(user.copy(password = hashed))
        return ResponseEntity.ok("User created")
    }

    @PostMapping("/login")
    fun login(@RequestBody user: User): ResponseEntity<String> {
        val dbUser = userRepository.findByUsername(user.username)
            ?: return ResponseEntity.status(401).body("Invalid credentials")

        return if (passwordEncoder.matches(user.password, dbUser.password)) {
            val token = jwtUtil.generateToken(user.username)
            ResponseEntity.ok(token)
        } else {
            ResponseEntity.status(401).body("Invalid credentials")
        }
    }
}
