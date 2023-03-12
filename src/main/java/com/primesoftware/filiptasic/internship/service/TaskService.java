package com.primesoftware.filiptasic.internship.service;

import com.primesoftware.filiptasic.internship.exception.EmployeeNotFoundException;
import com.primesoftware.filiptasic.internship.exception.TaskNotFoundException;
import com.primesoftware.filiptasic.internship.model.Employee;
import com.primesoftware.filiptasic.internship.model.Task;
import com.primesoftware.filiptasic.internship.repository.EmployeeRepository;
import com.primesoftware.filiptasic.internship.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().collect(Collectors.toList());
    }

    public Task getTask(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found"));
    }

    public List<Task> getTasksFromOneMonthAgo(){
        List<Task>taskList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate end  = LocalDate.now().minusDays(30);
        List<Task> tasks = getAllTasks();
        for(Task task: tasks){
            if(task.getDueDate().isBefore(today) && task.getDueDate().isAfter(end)){
                taskList.add(task);
            }
        }
        return taskList;
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public Task editTask(Task task, Long taskId) {
        Task task1 = taskRepository.findById(taskId).get();
        task1.setTitle(task.getTitle());
        task1.setDescription(task.getDescription());
        task1.setAssignee(task.getAssignee());
        task1.setDueDate(task.getDueDate());
        taskRepository.save(task1);
        return task1;
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task addTaskToEmployee(Long taskId, Long employeeId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        task.setAssignee(employee);
        taskRepository.save(task);
        return task;
    }
}
