package com.bonam.crud.api.v1.model.input

import io.swagger.v3.oas.annotations.media.Schema

data class TaskResponseDTO(
    @Schema(description = "ID da tarefa", example = "1")
    var id: Long?,

    @Schema(description = "Nome da tarefa", example = "Comprar leite")
    var name: String,

    @Schema(description = "Descrição da tarefa", example = "Ir ao mercado e comprar leite e pão")
    var description: String,

    @Schema(description = "Tags associadas à tarefa", example = "[\"compras\", \"mercado\"]")
    var tags: List<String>?
)
