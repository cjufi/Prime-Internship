package com.primesoftware.filiptasic.internship.controller;

import com.primesoftware.filiptasic.internship.model.Task;
import com.primesoftware.filiptasic.internship.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
    }

    @GetMapping("/last-month")
    public ResponseEntity<List<Task>> getTasksFromOneMonthAgo(){
        return new ResponseEntity<>(taskService.getTasksFromOneMonthAgo(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> editTask(@RequestBody Task task, @PathVariable Long id) {
        return new ResponseEntity<>(taskService.editTask(task, id), HttpStatus.OK);
    }

    @PutMapping("/{task_id}/{employee_id}")
    public ResponseEntity<Task> addTaskToEmployee(@PathVariable(name = "task_id") Long taskId, @PathVariable(name = "employee_id") Long employeeId) {
        return new ResponseEntity<>(taskService.addTaskToEmployee(taskId, employeeId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}