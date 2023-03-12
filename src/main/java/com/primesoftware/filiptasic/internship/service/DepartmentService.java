package com.primesoftware.filiptasic.internship.service;

import com.primesoftware.filiptasic.internship.exception.DepartmentNotFoundException;
import com.primesoftware.filiptasic.internship.exception.EmployeeNotFoundException;
import com.primesoftware.filiptasic.internship.model.Department;
import com.primesoftware.filiptasic.internship.model.Employee;
import com.primesoftware.filiptasic.internship.repository.DepartmentRepository;
import com.primesoftware.filiptasic.internship.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with id " + departmentId + " not found"));
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department editDepartment(Long departmentId, Department department) {
        Department department1 = getDepartment(departmentId);
        department1.setName(department.getName());
        department1.setColleagues(department.getColleagues());
        departmentRepository.save(department1);
        return department1;
    }

    public void deleteDepartment(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    public void addDepartmentToEmployee(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        employeeRepository.save(employee);
        department.getColleagues().add(employee);
        departmentRepository.save(department);
    }
}