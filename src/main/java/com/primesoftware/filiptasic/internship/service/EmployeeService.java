package com.primesoftware.filiptasic.internship.service;

import com.primesoftware.filiptasic.internship.exception.EmployeeNotFoundException;
import com.primesoftware.filiptasic.internship.model.Department;
import com.primesoftware.filiptasic.internship.model.Employee;
import com.primesoftware.filiptasic.internship.model.Task;
import com.primesoftware.filiptasic.internship.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TaskService taskService;
    private final DepartmentService departmentService;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + employeeId + " not found"));
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee editEmployee(Employee employee, Long employeeId) {
        Employee employee1 = employeeRepository.findById(employeeId).get();
        employee1.setFullName(employee.getFullName());
        employee1.setEmail(employee.getEmail());
        employee1.setMonthlySalary(employee.getMonthlySalary());
        employee1.setDateOfBirth(employee.getDateOfBirth());
        employee1.setPhoneNumber(employee.getPhoneNumber());
        employeeRepository.save(employee1);
        return employee1;
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> getEmployeesWithMostTasks() {
        List<Employee> employees = getAllEmployees();
        List<Task> tasks = taskService.getTasksFromOneMonthAgo();

        // Map employee IDs and task counts
        Map<Long, Integer> taskCounts = new HashMap<>();

        // Iterate through all tasks and count for each employee
        for (Employee employee : employees) {
            int count = 0;
            for (Task task : tasks) {
                if(task.getAssignee() == null){
                    continue;
                }
                if (task.getAssignee().equals(employee)) {
                    count++;
                }
            }
            taskCounts.put(employee.getEmployeeId(), count);
        }
        // Sort the map by task count and return the top 5 employees
        List<Map.Entry<Long, Integer>> sortedEntries = new ArrayList<>(taskCounts.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<Employee> topEmployees = new ArrayList<>();
        int i = 0;
        for (Map.Entry<Long, Integer> entry : sortedEntries) {
            if (i == 5) {
                break;
            }
            topEmployees.add(employeeRepository.findById(entry.getKey()).orElse(null));
            i++;
        }
        return topEmployees;
    }

    public List<Employee> getTopEarnersInDepartment(Long departmentId){
        Department department = departmentService.getDepartment(departmentId);
        //maps employee's id and salary
        Map<Long, Double> payChecks = new HashMap<>();

        for(Employee employee: department.getColleagues()){
            if(employee.getMonthlySalary() == -1){ // if the employee doesn't have the pay defined yet
                continue;
            }
            payChecks.put(employee.getEmployeeId(), employee.getMonthlySalary());
        }

        //Sort
        List<Map.Entry<Long, Double>> sortedEntries = new ArrayList<>(payChecks.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<Employee> sortedEmployees = new ArrayList<>();
        for(Map.Entry<Long, Double> entry: sortedEntries){
            sortedEmployees.add(employeeRepository.findById(entry.getKey()).orElse(null));
        }
        return sortedEmployees;
    }

    public List<Employee> getEmployeesFromDepartment(Long departmentId) {
        Department department = departmentService.getDepartment(departmentId);
        return department.getColleagues();
    }
}