package com.bonam.crud.api.v1.controller

import com.bonam.crud.api.v1.model.Role
import com.bonam.crud.domain.model.User
import com.bonam.crud.domain.repository.UserRepository
import com.bonam.crud.infraestructure.security.JwtUtil
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.parameters.RequestBody as SwaggerRequestBody
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para registro e login de usuários")
class AuthController(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/register")
    @Operation(
        summary = "Registrar novo usuário",
        description = "Cria um novo usuário com a role padrão USER",
        requestBody = SwaggerRequestBody(
            required = true,
            content = [Content(schema = Schema(implementation = User::class))]
        ),
        responses = [
            ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            ApiResponse(responseCode = "400", description = "Usuário já existe")
        ]
    )
    fun register(@RequestBody user: User): ResponseEntity<String> {
        if (userRepository.findByUsername(user.username) != null) {
            return ResponseEntity.badRequest().body("User already exists")
        }
        val hashed = passwordEncoder.encode(user.password)
        val userWithRole = user.copy(password = hashed, role = Role.USER)
        userRepository.save(userWithRole)
        return ResponseEntity.ok("User created")
    }

    data class LoginRequest(val username: String, val password: String)

    @PostMapping("/login")
    @Operation(
        summary = "Login do usuário",
        description = "Autentica usuário e retorna token JWT",
        requestBody = SwaggerRequestBody(
            required = true,
            content = [Content(schema = Schema(implementation = LoginRequest::class))]
        ),
        responses = [
            ApiResponse(responseCode = "200", description = "Login efetuado com sucesso, token JWT retornado"),
            ApiResponse(responseCode = "401", description = "Credenciais inválidas")
        ]
    )
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<String> {
        val dbUser = userRepository.findByUsername(loginRequest.username)
            ?: return ResponseEntity.status(401).body("Invalid credentials")

        return if (passwordEncoder.matches(loginRequest.password, dbUser.password)) {
            val token = jwtUtil.generateToken(dbUser.username)
            ResponseEntity.ok(token)
        } else {
            ResponseEntity.status(401).body("Invalid credentials")
        }
    }
}
