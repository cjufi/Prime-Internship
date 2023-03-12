package com.primesoftware.filiptasic.internship.controller;

import com.primesoftware.filiptasic.internship.model.Employee;
import com.primesoftware.filiptasic.internship.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    @GetMapping("/best")
    public List<Employee> findEmployeesWithMostTasks() {
        return employeeService.getEmployeesWithMostTasks();
    }

    @GetMapping("/sort/{department_id}")
    public ResponseEntity<List<Employee>> getTopEarnersInDepartment(@PathVariable Long department_id){
        return new ResponseEntity<>(employeeService.getTopEarnersInDepartment(department_id), HttpStatus.OK);
    }

    @GetMapping("/department/{id}")
    public List<Employee> findEmployeesFromDepartment(@PathVariable Long id){
        return employeeService.getEmployeesFromDepartment(id);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> editEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return new ResponseEntity<>(employeeService.editEmployee(employee, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
