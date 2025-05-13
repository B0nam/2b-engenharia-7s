package com.bonam.crud.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "TASK")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,
    var name: String,
    var description: String,
    var tags: List<String>?
) {
    constructor(id: Long, name: String, description: String) : this(id, name, description, null)
    constructor(id: Long, name: String) : this(id, name, "", null)
}
