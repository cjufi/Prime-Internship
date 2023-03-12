package com.primesoftware.filiptasic.internship.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;//(format:"yyyy-MM-dd")
    private double monthlySalary;
}