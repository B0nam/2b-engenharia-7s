package com.bonam.crud.domain.model

import jakarta.persistence.*

@Entity(name = "APP_USER")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long?,

    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String
) {
    constructor() : this(null, "", "")
}
