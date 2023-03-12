package com.primesoftware.filiptasic.internship.controller;

import com.primesoftware.filiptasic.internship.model.Department;
import com.primesoftware.filiptasic.internship.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments(){
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.getDepartment(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
        return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> editDepartment(@PathVariable Long id, @RequestBody Department department){
        return new ResponseEntity<>(departmentService.editDepartment(id, department),HttpStatus.OK);
    }

    @PutMapping("/{department_id}/{employee_id}")
    public void addDepartmentToEmployee(@PathVariable(name = "department_id") Long departmentId, @PathVariable(name = "employee_id") Long employeeId){
        departmentService.addDepartmentToEmployee(departmentId,employeeId);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
    }
}
