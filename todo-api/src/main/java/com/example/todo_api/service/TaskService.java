package com.example.todo_api.service;

import com.example.todo_api.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final Map<Long, Task> taskMap = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Task> getAll() {
        return new ArrayList<>(taskMap.values());
    }

    public Task create(Task task) {
        long id = idCounter.getAndIncrement();
        task.setId(id);
        taskMap.put(id, task);
        return task;
    }

    public Task update(Long id, Task updatedTask) {
        Task task = taskMap.get(id);
        if (task != null) {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setIsDone(updatedTask.isIsDone());
            return task;
        }
        throw new NoSuchElementException("Task not found");
    }

    public void delete(Long id) {
        taskMap.remove(id);
    }
}
