package com.bonam.crud.api.v1.controller

import com.bonam.crud.api.v1.model.input.TaskResponseDTO
import com.bonam.crud.domain.service.TaskService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(var service: TaskService) {

    @GetMapping("/")
    fun getAllTasks(): List<TaskResponseDTO> = service.getAllTask()
}
