package com.primesoftware.filiptasic.internship.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee assignee;
    private LocalDate dueDate; //(format:"yyyy-MM-dd")
}
