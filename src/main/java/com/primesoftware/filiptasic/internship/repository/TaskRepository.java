package com.primesoftware.filiptasic.internship.repository;

import com.primesoftware.filiptasic.internship.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {}
