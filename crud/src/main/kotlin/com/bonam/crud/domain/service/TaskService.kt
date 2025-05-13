package com.bonam.crud.domain.service

import com.bonam.crud.api.v1.model.input.TaskResponseDTO
import com.bonam.crud.api.v1.model.mapper.toDto
import com.bonam.crud.domain.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(var repository: TaskRepository) {
    fun getAllTask(): List<TaskResponseDTO> = repository.findAll().map { it.toDto() }
}
