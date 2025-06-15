package com.bonam.crud.domain.model

import com.bonam.crud.api.v1.model.Role
import jakarta.persistence.*

@Entity(name = "APP_USER")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long?,

    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: Role = Role.USER
) {
    constructor() : this(null, "", "", Role.USER)
}
