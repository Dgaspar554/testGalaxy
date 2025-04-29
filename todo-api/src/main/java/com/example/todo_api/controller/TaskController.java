package com.example.todo_api.controller;

import com.example.todo_api.model.Task;
import com.example.todo_api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tareas", description = "API para gestión de tareas (crear, listar, actualizar, eliminar)")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    @Operation(summary = "Obtener todas las tareas", description = "Devuelve una lista con todas las tareas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tareas obtenida correctamente")
    })
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @PostMapping
    @Operation(summary = "Crear una nueva tarea", description = "Agrega una nueva tarea con título y estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea creada exitosamente")
    })
    public Task create(
            @RequestBody Task task) {
        return taskService.create(task);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una tarea", description = "Actualiza una tarea existente mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea actualizada correctamente")
    })
    public Task update(
            @Parameter(description = "ID de la tarea a actualizar") @PathVariable Long id,
            @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una tarea", description = "Elimina una tarea por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea eliminada correctamente")
    })
    public void delete(
            @Parameter(description = "ID de la tarea a eliminar") @PathVariable Long id) {
        taskService.delete(id);
    }
}
