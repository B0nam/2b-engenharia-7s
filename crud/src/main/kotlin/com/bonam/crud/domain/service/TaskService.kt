package com.bonam.crud.domain.service

import com.bonam.crud.api.v1.model.input.TaskRequestDTO
import com.bonam.crud.api.v1.model.input.TaskResponseDTO
import com.bonam.crud.api.v1.model.mapper.toDto
import com.bonam.crud.domain.model.Task
import com.bonam.crud.domain.repository.TaskRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class TaskService(var repository: TaskRepository) {

    fun getAllTask(): List<TaskResponseDTO> = repository.findAll().map { it.toDto() }

    fun getTaskById(id: Long): TaskResponseDTO? = repository.findById(id).getOrNull()?.toDto();

    fun addTask(taskRequestDTO: TaskRequestDTO): TaskResponseDTO {
        val newTask = Task(null, taskRequestDTO.name, taskRequestDTO.description, taskRequestDTO.tags)
        return repository.save(newTask).toDto()
    }

    fun editTaskById(id: Long, taskRequestDTO: TaskRequestDTO): TaskResponseDTO {
        val actualTask: Task = repository.findById(id).orElseThrow()
        actualTask.name = taskRequestDTO.name
        actualTask.description = taskRequestDTO.description
        actualTask.tags = taskRequestDTO.tags
        return repository.save(actualTask).toDto()
    }

    fun deleteTaskById(id: Long) {
        val task: Task = repository.findById(id).orElseThrow();
        repository.delete(task)
    }
}
