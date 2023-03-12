package com.primesoftware.filiptasic.internship.repository;

import com.primesoftware.filiptasic.internship.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
