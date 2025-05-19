package com.bonam.crud.infraestructure.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil {
    private val secret: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)
    private val expirationTime = 1000 * 60 * 60 // 1 hora

    fun generateToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(secret)
            .compact()
    }

    fun validateToken(token: String): String? {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .body
            claims.subject
        } catch (e: Exception) {
            null
        }
    }
}
