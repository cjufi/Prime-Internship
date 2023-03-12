package com.primesoftware.filiptasic.internship.repository;

import com.primesoftware.filiptasic.internship.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
