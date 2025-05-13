package com.bonam.crud.api.v1.model.mapper

import com.bonam.crud.api.v1.model.input.TaskResponseDTO
import com.bonam.crud.domain.model.Task

fun Task.toDto(): TaskResponseDTO = TaskResponseDTO(this.id, this.name, this.description, this.tags)
