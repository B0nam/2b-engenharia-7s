package com.bonam.crud.api.v1.model.input

data class TaskRequestDTO(
    var name: String,
    var description: String,
    var tags: List<String>?,
)
