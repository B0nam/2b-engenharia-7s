package com.bonam.crud.api.v1.model.input

data class TaskResponseDTO(
    var id: Long?,
    var name: String,
    var description: String,
    var tags: List<String>?
)
