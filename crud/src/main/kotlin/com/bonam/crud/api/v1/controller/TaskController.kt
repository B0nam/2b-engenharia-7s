package com.bonam.crud.api.v1.controller

import com.bonam.crud.api.v1.model.input.TaskRequestDTO
import com.bonam.crud.api.v1.model.input.TaskResponseDTO
import com.bonam.crud.domain.service.TaskService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.parameters.RequestBody as SwaggerRequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/task")
@Tag(name = "Tarefas", description = "Gerenciamento de tarefas")
class TaskController(private val service: TaskService) {

    @GetMapping
    @Operation(
        summary = "Listar todas as tarefas",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Lista de tarefas",
                content = [
                    Content(
                        mediaType = "application/json",
                        array = io.swagger.v3.oas.annotations.media.ArraySchema(schema = Schema(implementation = TaskResponseDTO::class))
                    )
                ]
            )
        ]
    )
    fun getAllTasks(): List<TaskResponseDTO> = service.getAllTask()

    @PostMapping
    @Operation(
        summary = "Criar nova tarefa",
        requestBody = SwaggerRequestBody(
            required = true,
            content = [Content(schema = Schema(implementation = TaskRequestDTO::class))]
        ),
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Tarefa criada",
                content = [Content(schema = Schema(implementation = TaskResponseDTO::class))]
            )
        ]
    )
    fun addTask(@RequestBody taskRequestDTO: TaskRequestDTO): TaskResponseDTO = service.addTask(taskRequestDTO)

    @PatchMapping("/{id}")
    @Operation(
        summary = "Editar tarefa existente",
        requestBody = SwaggerRequestBody(
            required = true,
            content = [Content(schema = Schema(implementation = TaskRequestDTO::class))]
        ),
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Tarefa editada",
                content = [Content(schema = Schema(implementation = TaskResponseDTO::class))]
            )
        ]
    )
    fun editTask(@PathVariable id: Long, @RequestBody taskRequestDTO: TaskRequestDTO): TaskResponseDTO =
        service.editTaskById(id, taskRequestDTO)

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar tarefa pelo ID",
        responses = [ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")]
    )
    fun deleteTaskById(@PathVariable id: Long) = service.deleteTaskById(id)
}
