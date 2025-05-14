package com.bonam.crud.api.v1.controller

import com.bonam.crud.api.v1.model.input.TaskRequestDTO
import com.bonam.crud.api.v1.model.input.TaskResponseDTO
import com.bonam.crud.domain.service.TaskService
import org.springframework.web.bind.annotation.*

@RestController
@ResponseBody
@RequestMapping("/task")
class TaskController(var service: TaskService) {

    @GetMapping
    fun getAllTasks(): List<TaskResponseDTO> = service.getAllTask()

    @PostMapping
    fun addTask(taskRequestDTO: TaskRequestDTO): TaskResponseDTO = service.addTask(taskRequestDTO)

    @PatchMapping("/{id}")
    fun editTask(@PathVariable id: Long, taskRequestDTO: TaskRequestDTO): TaskResponseDTO =
        service.editTaskById(id, taskRequestDTO)

    @DeleteMapping("/{id}")
    fun deleteTaskById(@PathVariable id: Long) = service.deleteTaskById(id)
}
